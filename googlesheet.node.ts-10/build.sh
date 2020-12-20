#!/bin/bash
cd "$(dirname $0)"
ZIP_FILE=$(basename $(pwd)).zip
if ! test -d node_modules
then echo "downloading libraries, please wait..."
     npm install
fi
if ! test -f $ZIP_FILE 
then echo "preparing archive, please wait..."
     zip -qr $ZIP_FILE node_modules
fi
npm run tsc
cd dist || { echo "dist directory not found, aborting..."; exit 1; }
zip -u ../$ZIP_FILE *.js -x *.test.js
cd ..
#wsk action update iosdk/import index.zip --kind nodejs:10 --web true
 
