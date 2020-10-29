import { config } from "./config";

// extract and convert fields using the config
function extract(key: string, data: {}) {
  let field_map = config.field_map;
  let convert = config.convert;
  let value: string;
  if (key in field_map) {
    value = data[field_map[key]];
  }
  if (key in convert) {
    value = convert[key](value);
  }
  return value;
}

export function translate(data) {
  return {
    time_to_live: config.time_to_live,
    subject: extract("subject", data),
    markdown: extract("markdown", data),
    due_date: extract("due_date", data),
    fiscal_code: extract("fiscal_code", data),
  };
}
