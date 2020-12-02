package main

import (
	"context"
	"fmt"
	"testing"

	"mongoconnector/src/iosdkmongo"
)

func TestMessages(t *testing.T) {
	store := iosdkmongo.ConnectForTest(context.Background())

	// create a collection and fill it with documents for the test - to be deleted at the end of the test
	type collDoc struct {
		FiscalCode string `bson:"fiscal_code"`
		Subject    string
		Markdown   string
		DueDate    string `bson:"due_date"`
	}
	fiscalCode := "AAAAAA00A00A000A"
	subject := "Hi Cocoon Techie"
	markdown := "Welcome, Cocoon Techie\nThis is a test message.\n.This message was sent using the IO-SDK.\n"
	dueDate := "2001-01-01"
	docs := []interface{}{
		collDoc{fiscalCode, subject, markdown, dueDate},
	}
	testCollName := iosdkmongo.SetupCollection(context.Background(), store, docs)
	defer iosdkmongo.DeleteCollection(context.Background(), testCollName, store)

	msgs := messages(iosdkmongo.ConnectionString(), iosdkmongo.TestDbName, testCollName)

	if len(msgs) != len(docs) {
		t.Errorf("Retrieved %v messages while expecting %v", len(msgs), len(docs))
	}
	firstMsg := msgs[0]
	if firstMsg["Destinatario"] != fiscalCode {
		t.Errorf("fiscal_code %v not as expected %v", firstMsg["Destinatario"], fiscalCode)
	}
	if firstMsg["Titolo"] != subject {
		t.Errorf("subject %v not as expected %v", firstMsg["Titolo"], subject)
	}
	if firstMsg["Testo"] != markdown {
		t.Errorf("markdown '%v' not as expected '%v'", firstMsg["Testo"], markdown)
	}
	if firstMsg["Scadenza"] != dueDate {
		t.Errorf("due_date %v not as expected %v", firstMsg["Scadenza"], dueDate)
	}
}

func TestRespForMessages(t *testing.T) {
	store := iosdkmongo.ConnectForTest(context.Background())

	// create a collection and fill it with documents for the test - to be deleted at the end of the test
	type collDoc struct {
		FiscalCode string `bson:"fiscal_code"`
		Subject    string
		Markdown   string
		DueDate    string `bson:"due_date"`
	}
	fiscalCode := "AAAAAA00A00A000A"
	subject := "Hi Cocoon Techie"
	markdown := "Welcome, Cocoon Techie\nThis is a test message.\n.This message was sent using the IO-SDK.\n"
	dueDate := "2001-01-01"
	docs := []interface{}{
		collDoc{fiscalCode, subject, markdown, dueDate},
	}
	testCollName := iosdkmongo.SetupCollection(context.Background(), store, docs)
	defer iosdkmongo.DeleteCollection(context.Background(), testCollName, store)

	resp := respForMessages(iosdkmongo.ConnectionString(), iosdkmongo.TestDbName, testCollName)

	fmt.Println("resp", resp)

	body, okResp := resp["body"]
	if okResp != true {
		t.Errorf("There is no body in the resp")
	}

	bodyMap := body.(map[string]interface{})
	data, okData := bodyMap["data"]
	if okData != true {
		t.Errorf("There is no data in the resp")
	}

	msgs := data.([]map[string]interface{})
	fmt.Println(msgs)

	if len(msgs) != len(docs) {
		t.Errorf("Retrieved %v messages while expecting %v", len(msgs), len(docs))
	}
	firstMsg := msgs[0]
	if firstMsg["Destinatario"] != fiscalCode {
		t.Errorf("fiscal_code %v not as expected %v", firstMsg["Destinatario"], fiscalCode)
	}
	if firstMsg["Titolo"] != subject {
		t.Errorf("subject %v not as expected %v", firstMsg["Titolo"], subject)
	}
	if firstMsg["Testo"] != markdown {
		t.Errorf("markdown '%v' not as expected '%v'", firstMsg["Testo"], markdown)
	}
	if firstMsg["Scadenza"] != dueDate {
		t.Errorf("due_date %v not as expected %v", firstMsg["Scadenza"], dueDate)
	}
}
