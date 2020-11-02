package main

import "fmt"

// Main function for the action
func Main(obj map[string]interface{}) map[string]interface{} {
	credentials, ok := obj["credentials"].(string)
	if !ok {
		return respForFormStatic()
	}
	fmt.Printf("credentials=%s\n", credentials)
	msg := make(map[string]interface{})
	return msg
}
