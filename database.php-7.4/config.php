<?php
    // describe the form - supported field are in the list
    $emptyForm = <<<EOD
    {"form": [
        {
            "type": "message",
            "name": "note",
            "description": "Connect to postgres db to import messages"
        },
        {
            "name": "dbhost",
            "description": "DbHost",
            "type": "string",
            "required": false
        },
        {
            "name": "dbport",
            "description": "DbPort",
            "type": "string",
            "required": false
        },
        {
            "name": "dbname",
            "description": "DbName",
            "type": "string",
            "required": true
        },
        {
            "name": "dbuser",
            "description": "DbUser",
            "type": "string",
            "required": true
        },
        {
            "name": "dbpass",
            "description": "DbPass",
            "type": "password",
            "required": true
        }
  ]}
  EOD
?>
