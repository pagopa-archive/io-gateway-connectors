import { readSheetRawData$ } from "../connector";

// see https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
// spreadsheetId = "1ymaoVSpOXzAaNifwelQNa5AlAG5EBuC7R5cpaPCYAYY";
async function readSpreadsheet() {
  const credentials = process.argv[2];
  const token = process.argv[3];
  const spreadsheetId = process.argv[4];
  const sheet = process.argv[5];
  console.log("Fetching data ...");
  readSheetRawData$(
    credentials,
    token,
    spreadsheetId,
    sheet
  ).subscribe((data) =>
    console.log("Data retrieved", JSON.stringify(data, null, 2))
  );
}

readSpreadsheet();
