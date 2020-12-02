// Implements async functions as Observables

import { google } from "googleapis";
import { OAuth2Client } from "google-auth-library";

import { Observable, Observer, TeardownLogic } from "rxjs";

export function readGoogleSpreadsheet$(
  spreadsheetId: string,
  sheet: string,
  auth: OAuth2Client
) {
  const sheets = google.sheets({ version: "v4", auth });
  return new Observable(
    (observer: Observer<any[][]>): TeardownLogic => {
      sheets.spreadsheets.values.get(
        {
          spreadsheetId,
          range: sheet,
        },
        (err: Error, res: any) => {
          if (err) {
            observer.error(err);
            return;
          }
          const rows: any[][] = res.data.values;
          observer.next(rows);
          observer.complete();
        }
      );
    }
  );
}
