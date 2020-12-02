/*
* Read the configuration from a config file
 */

package main

import (
	"encoding/json"
	"io/ioutil"
	"log"
	"os"
)

// ReadFormAsMaps reads form data from config and returns it in form of slide of maps
func ReadFormAsMaps() []map[string]interface{} {
	// @todo Need to understand how to locate config.json in OpenWhisk
	config, err := os.Open("config.json")
	if err != nil {
		log.Fatalln(err)
	}
	defer config.Close()

	byteValue, _ := ioutil.ReadAll(config)

	var result map[string]interface{}
	json.Unmarshal([]byte(byteValue), &result)

	_form := result["form"].([]interface{})
	form := make([]map[string]interface{}, 0)
	for _, v := range _form {
		form = append(form, v.(map[string]interface{}))
	}

	return form

}

func respForForm() map[string]interface{} {
	body := make(map[string]interface{})
	form := make(map[string]interface{})
	form["form"] = ReadFormAsMaps()
	body["body"] = form
	return body
}
