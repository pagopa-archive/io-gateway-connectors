#!/bin/bash
cd "$(dirname $0)"
if ! test -d node_modules
then echo "downloading libraries, please wait..."
     npm install
fi
if ! test -f index.zip 
then echo "preparing archive, please wait..."
     zip -qr index.zip node_modules
fi
npm run tsc
cd dist
zip -u ../index.zip *.js -x *.test.js
cd ..
wsk action update iosdk/import index.zip --kind nodejs:10 --web true
 
