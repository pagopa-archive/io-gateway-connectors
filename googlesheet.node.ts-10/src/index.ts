import { config } from "./config";
import { readSheetFromSdk } from "./connector";

export function main(args) {
  if (!args.credentials) {
    const body = { form: config.form };
    return { body };
  }
  let credentials = Buffer.from(args.credentials, "base64").toString("ascii");
  let token = Buffer.from(args.token, "base64").toString("ascii");
  return readSheetFromSdk(
    JSON.parse(credentials),
    JSON.parse(token),
    args.spreadsheetid,
    args.sheetname
  );
}
