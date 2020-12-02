package iosdkmongo

import (
	"context"
	"log"

	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
	"go.mongodb.org/mongo-driver/mongo/readpref"
)

// Store is the mongodb reference
type Store struct {
	db *mongo.Database
}

// Connect to db
func Connect(ctx context.Context, connString string, dbname string) *Store {
	clientOptions := options.Client().ApplyURI(connString)
	client, err := mongo.NewClient(clientOptions)
	if err != nil {
		log.Fatal("Error creating Mongo Client", err)
	}

	err = client.Connect(ctx)
	if err != nil {
		log.Fatal("Error connecting to Mongo", err)
	}

	err = client.Ping(context.Background(), readpref.Primary())
	if err != nil {
		log.Fatal("Couldn't connect to the database", err)
	} else {
		log.Println("Connected!")
	}
	var store = Store{
		db: client.Database(dbname),
	}
	return &store
}

// ReadCollection reads documents from a mongo collection
// https://stackoverflow.com/a/18346347/5699993
func (store *Store) ReadCollection(collName string) (documents []bson.M, err error) {
	// it is important to initialize documents because we do not want to retun nils but rather
	// an empty slice
	documents = make([]bson.M, 0)

	collection := store.db.Collection(collName)
	findOptions := options.Find()

	cur, err := collection.Find(context.TODO(), bson.D{}, findOptions)
	if err != nil {
		log.Print("Error while opening cursor: ", err)
		return
	}
	defer cur.Close(context.TODO())

	for cur.Next(context.TODO()) {
		var document bson.M
		err = cur.Decode(&document)
		if err != nil {
			log.Print("Error while reading cursor", err)
			return
		}
		documents = append(documents, document)
	}

	if err = cur.Err(); err != nil {
		log.Print("Error after having read cursor", err)
		return
	}

	return
}
