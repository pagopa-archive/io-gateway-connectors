/*
* Read the configuration from a global string
 */

package main

import (
	"encoding/json"
)

// ReadFormAsMapsStatic reads form data from config and returns it in form of slide of maps
func ReadFormAsMapsStatic() []map[string]interface{} {
	var result map[string]interface{}
	json.Unmarshal([]byte(configString), &result)

	_form := result["form"].([]interface{})
	form := make([]map[string]interface{}, 0)
	for _, v := range _form {
		form = append(form, v.(map[string]interface{}))
	}

	return form

}

func respForFormStatic() map[string]interface{} {
	body := make(map[string]interface{})
	form := make(map[string]interface{})
	form["form"] = ReadFormAsMapsStatic()
	body["body"] = form
	return body
}
