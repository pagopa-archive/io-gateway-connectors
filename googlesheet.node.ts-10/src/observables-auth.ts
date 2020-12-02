import { google } from "googleapis";
import { OAuth2Client, Credentials } from "google-auth-library";

import { question$, readFile$, writeFile$ } from "./observables";
import { catchError, concatMap, map, tap } from "rxjs/operators";
import { Observable, Observer, TeardownLogic } from "rxjs";

export function oAuth2Client$(credentials: string, token: string) {
  // Read client secrets and token from local files.
  return readCredentials$(credentials).pipe(
    concatMap((credentialsJson) =>
      readToken$(token).pipe(
        catchError((err) => {
          if (err.code === "ENOENT" && err.path === token) {
            return getNewToken$(credentialsJson, token);
          }
          throw err;
        }),
        map((tokenJson) => [credentialsJson, tokenJson])
      )
    ),
    map(([credentialsJson, tokenJson]) => {
      const oAuth2Client = buildOAuthClientAndSetToken(
        credentialsJson,
        tokenJson
      );
      return oAuth2Client;
    })
  );
}

export function buildOAuthClientAndSetToken(
  credentialsJson: any,
  tokenJson: any
) {
  const oAuthClient = buildOAuthClient(credentialsJson);
  oAuthClient.setCredentials(tokenJson);
  return oAuthClient;
}

function readCredentials$(file: string) {
  return readFile$(file).pipe(
    tap({
      error: (err) => console.log("Error reading client secret file:", err),
    }),
    map((credentials) => JSON.parse(credentials.toString()))
  );
}

function readToken$(file: string) {
  return readFile$(file).pipe(
    tap({
      error: (err) => console.log("Error reading token file:", err),
    }),
    map((token) => JSON.parse(token.toString()))
  );
}

// If modifying these scopes, delete token.json.
const SCOPES = ["https://www.googleapis.com/auth/spreadsheets.readonly"];
export function getNewToken$(credentials: any, tokenPath: string) {
  const oAuth2Client = buildOAuthClient(credentials);
  const authUrl = oAuth2Client.generateAuthUrl({
    access_type: "offline",
    scope: SCOPES,
  });
  console.log("Authorize this app by visiting this url:", authUrl);
  return question$("Enter the code from that page here: ").pipe(
    concatMap((code) => retrieveNewToken$(oAuth2Client, code)),
    concatMap((token) =>
      writeFile$(tokenPath, JSON.stringify(token)).pipe(map(() => token))
    ),
    tap({
      next: () => console.log("Token stored to", tokenPath),
      error: (err) =>
        console.error(
          `Error while trying to store token on file ${tokenPath}`,
          err
        ),
    })
  );
}
function buildOAuthClient(credentialsJson: any) {
  const { client_secret, client_id, redirect_uris } = credentialsJson.installed;
  const oAuthClient = new google.auth.OAuth2(
    client_id,
    client_secret,
    redirect_uris[0]
  );
  return oAuthClient;
}

function retrieveNewToken$(oAuth2Client: OAuth2Client, code: string) {
  return new Observable(
    (observer: Observer<Credentials>): TeardownLogic => {
      oAuth2Client.getToken(code, (err, token) => {
        if (err) {
          console.error("Error while trying to retrieve access token", err);
          observer.error(err);
          return;
        }
        observer.next(token);
      });
    }
  );
}
