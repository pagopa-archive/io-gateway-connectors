const fs = require('fs')
const importer = require("./importer")

test("importer", () => {
    let data = fs.readFileSync("data/test.xlsx").toString('base64')
    let imp = importer(data)
    expect(imp).toMatchSnapshot()
})