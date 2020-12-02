import { concatMap } from "rxjs/operators";
import { oAuth2Client$ } from "./observables-auth";
import { readGoogleSpreadsheet$ } from "./observables-googlesheets";

test("the observable returned by readGoogleSpreadsheet$ emits the rows in the test file", (done) => {
  // this is the test sheet
  const spreadsheetId = "17Lz6SadKDJR8c2m7080kIaJc24Azob0Gh9MhAeMVU8I";
  const sheet = "Messaggi";
  const credentialsFile = "./test-data/test-credentials.json";
  const tokenFile = "./test-data/test-token.json";
  oAuth2Client$(credentialsFile, tokenFile)
    .pipe(
      concatMap((oAuthClient) =>
        readGoogleSpreadsheet$(spreadsheetId, sheet, oAuthClient)
      )
    )
    .subscribe({
      next: (rows) => {
        expect(rows.length).toBe(3);
        rows.forEach((row) => {
          expect(row.length).toBe(4);
          console.log(row);
        });
        const headers = rows[0];
        expect(headers[0]).toBe("Destinatario");
        expect(headers[1]).toBe("Scadenza");
        expect(headers[2]).toBe("Titolo");
        expect(headers[3]).toBe("Testo");
      },
      complete: () => done(),
    });
});
