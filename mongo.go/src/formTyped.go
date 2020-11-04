/*
* Read the configuration from a config file and map it into typed structures
 */

package main

import (
	"encoding/json"
	"io/ioutil"
	"log"
	"os"
)

// FormField describes a field of the form
type FormField struct {
	Name        string `json:"name"`
	Description string `json:"description"`
	Type        string `json:"type"`
	Required    bool   `json:"required,omitempty"`
}

// Config is the json configuration
type Config struct {
	Form []FormField `json:"form"`
}

// ReadForm reads form data from config
func ReadForm() []FormField {
	config, err := os.Open("config.json")
	if err != nil {
		log.Fatalln(err)
	}
	defer config.Close()

	byteValue, _ := ioutil.ReadAll(config)

	var result Config
	json.Unmarshal([]byte(byteValue), &result)

	return result.Form

}
