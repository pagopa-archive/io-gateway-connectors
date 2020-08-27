#!/bin/bash
cd "$(dirname $0)"
if ! test -f index.zip 
then echo "preparing archive, please wait..."
    zip -qr index.zip index.php config.php
fi
wsk action update iosdk/import index.zip --kind php:7.4 --web true
 
