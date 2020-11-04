## Test

### Test from command line

In order to run the tests from within the root folder run the command `MONGO_CONNECTION="mongodb://user:password@host:port/?retryWrites=true&w=majority" go test ./...` where you need to substitute "mongodb://user:password@host:port/?retryWrites=true&w=majority" with the real connection string

### Test from VSCode

Open a test file, go to the "Run" view and select "Launch Package Tests" from the dropdown list. This will run all the tests of the package where the open test file resides

## Deploy

To deploy run `bash build.sh`
