export const config = {
  // Default time to live of a message
  time_to_live: 3600,
  // Mapping the Heading of the Excel file (first row containing column names) to message fields
  column_field_map: {
    Destinatario: "fiscal_code",
    Titolo: "subject",
    Testo: "markdown",
    Scadenza: "due_date",
  },
  // conversion functions you can apply to each field
  convert: {
    // convert Excel date in JavaScript date
    due_date: function (v) {
      return new Date(Math.round((v - 25569) * 86400 * 1000));
    },
  },
  // describe the form - supported field are in the list
  form: [
    // shows infomative message in the form
    {
      type: "message",
      name: "note",
      description: "Upload a Google sheet from the Cloud",
    },
    /*
        // a file field
        {
            "name": "file",
            "description": "Excel File",
            "type": "upload",
            "required": true
        }
        */
    // credentials file name
    {
      name: "credentials",
      description: "Credentials",
      type: "upload",
      required: true,
    },
    // token file name
    {
      name: "token",
      description: "Token",
      type: "upload",
      required: true,
    },
    // spreadsheet id
    {
      name: "spreadsheetid",
      description: "Spreadsheet Id",
      type: "string",
      required: true,
    },
    // sheet name
    {
      name: "sheetname",
      description: "Sheet Name",
      type: "string",
      required: true,
    },
    /*
        // a password field
        {
        "name": "password",
        "description": "Password",
        "type": "password",
        "required": true
        }*/
  ],
};
