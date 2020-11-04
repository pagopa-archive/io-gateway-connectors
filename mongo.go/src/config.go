package main

import "encoding/json"

const configString = `{
	"time_to_live": 3600,
	"column_field_map": {
	  "Destinatario": "fiscal_code",
	  "Titolo": "subject",
	  "Testo": "markdown",
	  "Scadenza": "due_date"
	},
	"form": [
	  {
		"type": "message",
		"name": "note",
		"description": "Upload a Mongo Collection"
	  },
	  {
		"name": "mongoUrl",
		"description": "Mongo Url",
		"type": "string",
		"required": true
	  },
	  {
		"name": "dbName",
		"description": "Database",
		"type": "string",
		"required": true
	  },
	  {
		"name": "collection",
		"description": "Collection",
		"type": "string",
		"required": true
	  }
	]
  }`

func config() map[string]interface{} {
	var cfg map[string]interface{}
	json.Unmarshal([]byte(configString), &cfg)
	return cfg
}
