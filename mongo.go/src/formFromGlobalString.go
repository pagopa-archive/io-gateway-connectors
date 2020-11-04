/*
* Read the configuration from a global string
 */

package main

// readFormAsMapsStatic reads form data from config and returns it in form of slide of maps
func readFormAsMapsStatic() []map[string]interface{} {
	config := config()

	_form := config["form"].([]interface{})
	form := make([]map[string]interface{}, 0)
	for _, v := range _form {
		form = append(form, v.(map[string]interface{}))
	}

	return form

}

func respForFormStatic() map[string]interface{} {
	body := make(map[string]interface{})
	form := make(map[string]interface{})
	form["form"] = readFormAsMapsStatic()
	body["body"] = form
	return body
}
