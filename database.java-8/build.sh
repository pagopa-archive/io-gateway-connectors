#!/bin/bash
if [[ $# -eq 0 ]] ; then
    echo 'Database type is required: mysql, oracle, sqlserver, postgresql'
    exit 0
fi
cd "$(dirname $0)"
echo "preparing archive, please wait..."
export ORG_GRADLE_PROJECT_databaseType=$1
echo "database.type=$1" > src/main/resources/config.properties
./gradlew distZip
