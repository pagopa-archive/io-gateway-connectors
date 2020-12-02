package iosdkmongo

import (
	"context"
	"fmt"
	"os"
	"time"
)

// TestDbName name of the test db
const TestDbName = "testDb"
const testCollPrefix = "testColl"

// ConnectionString to be used to connect to mongo for test
func ConnectionString() string {
	return os.Getenv("MONGO_CONNECTION")
}

// ConnectForTest return the store used for tests
func ConnectForTest(ctx context.Context) *Store {
	connString := ConnectionString()
	if connString == "" {
		panic("mongo connection string has not been provided - please set it in the env var MONGO_CONNECTION")
	}
	return Connect(ctx, connString, TestDbName)
}

// SetupCollection creates a collection and fills it with documents
func SetupCollection(ctx context.Context, store *Store, docs []interface{}) string {
	sec := time.Now().Unix()
	testCollName := fmt.Sprintf("%v_%v", testCollPrefix, sec)
	coll := store.db.Collection(testCollName)
	coll.InsertMany(ctx, docs)
	return testCollName
}

// DeleteCollection deletes a collection
func DeleteCollection(ctx context.Context, collName string, store *Store) {
	coll := store.db.Collection(collName)
	coll.Drop(ctx)
}
