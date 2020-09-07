#!/bin/bash
cd "$(dirname $0)"
ZIP_FILE=$(basename $(pwd)).zip
if ! test -d node_modules
then echo "downloading libraries, please wait..."
     npm install
fi
if ! test -f $ZIP_FILE
then echo "preparing archive $ZIP_FILE, please wait..."
     zip -qr $ZIP_FILE node_modules
fi
zip -u $ZIP_FILE *.js -x *.test.js
#wsk action update iosdk/import index.zip --kind nodejs:10 --web true
