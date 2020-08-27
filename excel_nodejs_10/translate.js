const config = require("./config")

// extract and convert fields using the config
function extract(key, data) {
    let field_map = config["field_map"]
    let convert = config["convert"]
    let value = undefined
    if(key in field_map) {
        value = data[field_map[key]]
    }
    if(key in convert) {
        value = convert[key](value)
    }
    return value
}

module.exports = function(data) {
    return {
        "time_to_live": config["time_to_live"],
        "subject": extract("subject", data),
        "markdown": extract("markdown", data),
        "due_date": extract("due_date", data),
        "fiscal_code": extract("fiscal_code", data)
    }
}