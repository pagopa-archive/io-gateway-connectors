#!/bin/bash
cd "$(dirname $0)"
ZIP_FILE=$(basename $(pwd)).zip
if ! test -f $ZIP_FILE
then echo "preparing archive $ZIP_FILE, please wait..."
     zip -qr $ZIP_FILE __main__.py config.py
fi
#wsk action update iosdk/import index.zip --kind python:3 --web true
 
