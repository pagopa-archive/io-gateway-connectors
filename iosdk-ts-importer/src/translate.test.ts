//const fs = require('fs')
import * as fs from "fs";
import { importer } from "./importer";
import { translate } from "./translate";

test("translate", () => {
  let imp = importer(fs.readFileSync("data/data.xlsx").toString("base64"));
  //console.log(imp)
  let tran = translate(imp[0]);
  //console.log(tran)
  expect(tran).toMatchSnapshot();
});
