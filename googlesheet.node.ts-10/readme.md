# iosdk connector foe Google sheets running on Node

This is an implementation of a connector to import data from Google sheets following the iosdk specs.

The connector is implemented in Typescript and uses RxJs Observables to manage asynchronous logic.
It runs on Node and it is tested with Node v10.22.1

## Retrieve credentials for your Google account

To use Google APIs we need to have our credentials saves in a file, which will then be passed to the coonector.

### Steps to retrieve credentials and token (as per Oct 2020 up to my understanding)

- Create a google account or choose the one to use
- Go to https://developers.google.com/sheets/api/quickstart/nodejs and click on "Enable sheet API" button
- Choose the name of the Google Cloud project where the credentials will be stored and click "Next"
- In the "Configure your OAuth client" choose "Desktop app" and click "Create"
- In the following panel, which is also the last, click "Download Client Configuration" which will download a "configuration.json" file, containing the required configuation information
- Move the "credentials.json" file to the project root folder
- Launch the command `node ./dist/cmd/read-spreadsheet.js credentials.json token.json 17Lz6SadKDJR8c2m7080kIaJc24Azob0Gh9MhAeMVU8I Messaggi` making sure that
  - The code has been compiled with the command `npm run tsc` (the above command assumes the output folder for the compilation is named `dist`)
  - The file `config.json` downloaded is placed in the root project folder
  - There is NO `token.json` file in the root project folder
  - The id of the spreadsheet to download is provided as third parameter (e.g. 1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms)
  - The name of the specific sheet to load is provided as fouth parameter
- Since there is no `tokoen.json` file, the program presents us, on the command line, a url to be visited to authorize the app to access the Google sheet
- Copy the url and paste it in the address bar of the browser
- If you have more than one Google account, select one that has access rights to the sheet you want to download
- Some "security warning panels" are presented and you need to accept to continue the process (in one panel you may have to click on the "Advanced" link to reach the possibility to accept and continue the process)
- In the last panel a token is presented; copy that token, paste in on the command line where the program is still waiting for this info to continue and press enter
- The program creates the `token.json` file with the required information and retrieves the data from the spreadsheet

## Commands to be run from command line

### Retrieve the data from the spreadsheet

Run the command `node ./dist/cmd/read-spreadsheet.js` passing the following parameters:

- the name of the file containing the credentials (see above how to retrieve them)
- the name of the file containing the token (see above how to retrieve it)
- the spreadsheet id
- the name of the sheet to load
  Example `node ./dist/cmd/read-spreadsheet.js credentials.json token.json 1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms "Class Data"`

## APIs

The APIs are defined in the connector.ts file

- `function readSheet$(credentials: string, token: string, spreadsheetId: string, sheet: string): Observable<{}[]>`: reads the data from the Google sheet and return them as an Observable that emits an array of Obejcts, each representing a record - the column->field mapping is governed by the configuration set in the "column_field_map" property of the config.ts file
- `function readSheetRawData$(credentials: string, token: string, spreadsheetId: string, sheet: string): Observable<[[[]>`: same as `readSheet$` but does not transform the rows read from the sheet to Objects (i.e. records) and therefore emits an Array of Arrays, each one representing a row of the sheet
- `readSheetFromSdk(credentialsJson: any, tokenJson: any, spreadsheetId: string, sheet: string): Promise<{ body: { data: {}[]; }; } | { body: { data: { error: string; }; }; }>`: reads the data from the Google sheet using credentials and token passed in as JS objects, for which reason is difficult to use from command line but can be used by the iosdk - returns an Observable that emits a JS object in the format expected by iodsk

## Test

Some tests can run only with valid credentials and token. In order to be able to execute them follow these steps

- Generate the credentials for your account and the token, if you do not have them already.
- Move the credentials file to ./test-data/test-credentials.json
- Move the token file to ./test-data/test-token.json

Now you should be able to run all tests

## Links to documentation

[Google Node.js quickstart](https://developers.google.com/sheets/api/quickstart/nodejs) - provides details on retrieving credentials and token and make Google Node APIs work
