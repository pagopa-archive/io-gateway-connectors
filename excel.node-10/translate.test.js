const fs = require('fs')
const importer = require("./importer")
const translate = require("./translate")

test("translate", () => {    
    let imp = importer(fs.readFileSync("data/data.xlsx").toString('base64'))
    //console.log(imp)
    let tran = translate(imp[0])
    //console.log(tran)
    expect(tran).toMatchSnapshot()
})