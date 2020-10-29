import { config } from "./config";
import { importer } from "./importer";
import { translate } from "./translate";

export function main(args: any) {
  let body: any = { form: config.form };
  if (args.file) {
    let rows = importer(args.file);
    let data = [];
    for (let row of rows) data.push(translate(row));
    body = { data: data };
  }
  return { body };
}
