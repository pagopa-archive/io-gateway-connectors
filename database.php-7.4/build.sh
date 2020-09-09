#!/bin/bash
cd "$(dirname $0)"
ZIP_FILE=$(basename $(pwd)).zip
if ! test -f $ZIP_FILE
then echo "preparing archive $ZIP_FILE, please wait..."
    zip -vqr $ZIP_FILE index.php config.php
fi
#wsk action update iosdk/import index.zip --kind php:7.4 --web true
 
