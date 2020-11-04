package main

import (
	"context"
	"fmt"
	"mongoconnector/src/iosdkmongo"
)

func messages(connString string, dbName string, collName string) []map[string]interface{} {
	store := iosdkmongo.Connect(context.Background(), connString, dbName)
	documents, err := store.ReadCollection(collName)
	if err != nil {
		errMsg := fmt.Sprintf("Error while reading from Mongo collection: %v", err)
		panic(errMsg)
	}

	messages := make([]map[string]interface{}, 0)
	config := config()
	colFieldMap := config["column_field_map"].(map[string]interface{})
	for _, doc := range documents {
		msg := make(map[string]interface{})
		messages = append(messages, msg)
		for k, v := range colFieldMap {
			msg[k] = doc[v.(string)]
		}
	}

	return messages
}

func respForMessages(connString string, dbName string, collName string) map[string]interface{} {
	body := make(map[string]interface{})
	data := make(map[string]interface{})
	data["data"] = messages(connString, dbName, collName)
	body["body"] = data
	return body
}
