#!/bin/bash
cd "$(dirname $0)"
if ! test -f index.zip 
then echo "preparing archive, please wait..."
     zip -qr index.zip __main__.py config.py
fi
wsk action update iosdk/import index.zip --kind python:3 --web true
 
