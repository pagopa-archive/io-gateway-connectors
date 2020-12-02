import { config } from "./config";
import { catchError, concatMap, map } from "rxjs/operators";
import { buildOAuthClientAndSetToken, oAuth2Client$ } from "./observables-auth";
import { readGoogleSpreadsheet$ } from "./observables-googlesheets";
import { of } from "rxjs";

/**
 * Reads the records of a specific sheet of a Google worksheet provided that paths to files containing
 * the right credentials and token are passed.
 * HOW TO GET A FRESH TOKEN
 * If the credentials are correct but the token file is not found, a command line dialogue is started. The program respond
 * with a url printed on the terminal. The user has to navigate to that url. After choosing the Google account corresponding
 * to the Credentials, on the browser will appear a string token which needs to be copied and pasted on the command line,
 * after which press ENTER. The program will create a file, under the name originally passed as parameter,
 * containing the token information and will proceed with the rest of the logic, downloading the data.
 *
 * Returns an Observable wich emits an array with all the records read.
 * Each record is an object whose fields contain the values of the corresponding sheet's columns.
 * The mapping from columns to fields is provided in the connector configuration file.
 * @param {string} credentials The path to the file containing the authorization client credentials.
 * @param {string} token The path to the file containing the token data.
 * @param {string} spreadsheetId The id of the preadsheet.
 * @param {string} sheet The name of the sheet to download (if the name contains spaces use double quotes, e.g. "Class Data").
 */
export function readSheet$(
  credentials: string,
  token: string,
  spreadsheetId: string,
  sheet: string
) {
  return readSheetRawData$(credentials, token, spreadsheetId, sheet).pipe(
    map(rowsToRecords)
  );
}

export function readSheetRawData$(
  credentials: string,
  token: string,
  spreadsheetId: string,
  sheet: string
) {
  // Read client secrets and token from local files. If token file is not found, launches the procedure to retrieve it
  // from the server and store it on the file system
  return oAuth2Client$(credentials, token).pipe(
    // Reads the rows from the specified sheet of the specified Google spreadsheet:
    concatMap((authClient) =>
      readGoogleSpreadsheet$(spreadsheetId, sheet, authClient)
    )
  );
}

/**
 * Behaves like 'readSheet$' function with the exception that requires regular objects for Credentials and Token and also
 * because it transform the records read from Google sheets to the format expected by the iosdk and returns a
 * Promise out of an Observable
 * @param {any} credentials An object containing the authorization client credentials.
 * @param {any} token An object containing the token data.
 * @param {string} spreadsheetId The id of the preadsheet.
 * @param {string} sheet The name of the sheet to download (if the name contains spaces use double quotes, e.g. "Class Data").
 */
export function readSheetFromSdk(
  credentialsJson: any, // JSON containing the credentials
  tokenJson: any, // JSON containing the token
  spreadsheetId: string,
  sheet: string
) {
  const oAuth2Client = buildOAuthClientAndSetToken(credentialsJson, tokenJson);

  return readGoogleSpreadsheet$(spreadsheetId, sheet, oAuth2Client)
    .pipe(
      map(rowsToRecords),
      map((records) => ({
        body: {
          data: records,
        },
      })),
      catchError((err) =>
        of({
          body: {
            data: {
              error: `The request has generated an error: ${err}`,
            },
          },
        })
      )
    )
    .toPromise();
}

/*
 * Private methods - defined as exported (i.e. public) just fortesting reasons
 */
export function rowsToRecords(rows: [][]) {
  if (rows.length === 0) {
    return null;
  }
  const header = rows[0];
  const { mapper, columnsNotMapped, fieldsNotMapped } = positionToFieldMapper(
    header,
    config.column_field_map
  );
  if (columnsNotMapped.length > 0) {
    throw new Error(
      `These columns "${columnsNotMapped}" are not mapped to any field in the message`
    );
  }
  if (fieldsNotMapped.length > 0) {
    throw new Error(
      `These fields "${fieldsNotMapped}" are not mapped to any column in the sheet`
    );
  }
  const rowsWithoutHeader = rows.slice(1);
  const records = rowsWithoutHeader.map((row) => rowToRecord(row, mapper));
  return records;
}

export type ColumnToFieldMapper = { position: number; field: string }[];
export function positionToFieldMapper(header: string[], columnFieldMap: {}) {
  const mapper: ColumnToFieldMapper = [];
  const columnsNotMapped: string[] = [];
  header.forEach((columnName, i) => {
    const field = columnFieldMap[columnName];
    field
      ? mapper.push({ position: i, field })
      : columnsNotMapped.push(columnName);
  });
  // finds not mapped fields
  const fieldsNotMapped: string[] = [];
  const allExpectedFields: string[] = Object.values(columnFieldMap);
  const mappedFields = mapper.map((fieldMapper) => fieldMapper.field);
  allExpectedFields.forEach((field) => {
    if (!mappedFields.includes(field)) {
      fieldsNotMapped.push(field);
    }
  });
  return { mapper, columnsNotMapped, fieldsNotMapped };
}

export function rowToRecord(row: string[], mapper: ColumnToFieldMapper) {
  const record = {};
  mapper.forEach(({ position, field }) => (record[field] = row[position]));
  return record;
}
