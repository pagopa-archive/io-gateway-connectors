#!/bin/bash
cd "$(dirname $0)"
ZIP_FILE=$(basename $(pwd)).zip
rm main.zip

cd "src"

zip -r ../$ZIP_FILE *

cd ..

#wsk action update iosdk/import main.zip --kind go:1.11 --web true
 