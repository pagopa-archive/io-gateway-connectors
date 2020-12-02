package main

// Main function for the action
func Main(obj map[string]interface{}) map[string]interface{} {
	mongoURL, ok := obj["mongoUrl"].(string)
	if !ok {
		return respForFormStatic()
	}
	dbName, ok := obj["dbName"].(string)
	collection, ok := obj["collection"].(string)

	return respForMessages(mongoURL, dbName, collection)
}
