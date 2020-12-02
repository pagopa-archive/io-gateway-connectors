#!/bin/bash
cd "$(dirname $0)"

rm main.zip

cd "src"

zip -r ../main.zip *

cd ..

wsk action update iosdk/import main.zip --kind go:1.11 --web true
 