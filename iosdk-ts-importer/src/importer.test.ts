//const fs = require('fs')
import * as fs from "fs";
import { importer } from "./importer";

test("importer", () => {
  let data = fs.readFileSync("data/test.xlsx").toString("base64");
  let imp = importer(data);
  expect(imp).toMatchSnapshot();
});
