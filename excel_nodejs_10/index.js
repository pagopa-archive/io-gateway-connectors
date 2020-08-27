const config = require("./config")
const importer = require("./importer")
const translate = require("./translate")

function main(args) {
    let body = { "form": config.form }
    if(args.file) {
        let rows = importer(args.file)
        let data = []
        for(row of rows) 
            data.push(translate(row))
        body = { data: data }
    }
    return { "body": body }
}

module.exports.main = main
