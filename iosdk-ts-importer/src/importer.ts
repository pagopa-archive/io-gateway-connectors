//const xlsx = require("xlsx")
import * as xlsx from "xlsx";

export function importer(data) {
  let workbook = xlsx.read(data, { type: "base64" });
  let name = workbook.SheetNames[0];
  let sheet = workbook.Sheets[name];
  let ranges = sheet["!ref"].split(":");
  let fromCol = ranges[0].charCodeAt(0);
  let toCol = ranges[1].charCodeAt(0);
  let fromRow = parseInt(ranges[0].substring(1));
  let toRow = parseInt(ranges[1].substring(1));
  let keys = [];
  let res = [];
  for (let r = fromRow; r <= toRow; r++) {
    let i = 0;
    let rec = {};
    for (let c = fromCol; c <= toCol; c++) {
      const cell = String.fromCharCode(c) + r;
      if (cell in sheet) {
        const value = sheet[cell].v;
        if (r == fromRow) {
          keys.push(value);
        } else {
          const key = keys[i];
          rec[key] = value;
        }
      }
      i++;
    }
    if (r != fromRow && Object.keys(rec).length > 0) {
      res.push(rec);
    }
  }
  return res;
}
