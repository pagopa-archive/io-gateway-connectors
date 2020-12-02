package iosdkmongo

import (
	"context"
	"testing"
)

func TestReadCollection(t *testing.T) {
	store := ConnectForTest(context.Background())

	// create a collection and fill it with documents for the test - to be deleted at the end of the test
	type collDoc struct {
		FiscalCode string `bson:"fiscal_code"`
		Subject    string
		Markdown   string
		DueDate    string `bson:"due_date"`
	}
	docs := []interface{}{
		collDoc{"AAAAAA00A00A000A", "Hi Cocoon Techie", "Welcome, Cocoon Techie\nThis is a test message.\n.This message was sent using the IO-SDK.\n", "2001-01-01"},
	}
	testCollName := SetupCollection(context.Background(), store, docs)
	defer DeleteCollection(context.Background(), testCollName, store)

	docsRead, err := store.ReadCollection(testCollName)
	if err != nil {
		t.Errorf("Error while reading collection: '%v'", err)
	}
	if len(docsRead) != len(docs) {
		t.Errorf("Read %v docs while expecting %v docs", len(docsRead), len(docs))
	}
	firstDoc := docs[0]
	firstDocRead := docsRead[0]
	fiscalCodeExpected := firstDoc.(collDoc).FiscalCode
	fiscalCodeRead := firstDocRead["fiscal_code"]
	if fiscalCodeExpected != fiscalCodeRead {
		t.Errorf("Fiscal code expected '%v' is not the read one '%v'", fiscalCodeExpected, fiscalCodeRead)
	}
	// reflect.TypeOf(docsRead[0])
	// fmt.Println(reflect.TypeOf(docsRead[0]))
	// fmt.Println(docsRead[0])

	// for key, value := range docsRead[0] {
	// 	fmt.Println(key, value)
	// 	fmt.Println(reflect.TypeOf(value))
	// }
}
