set -m
sudo MSSQL_IP_ADDRESS=0.0.0.0 /opt/mssql/bin/mssql-conf setup
/opt/mssql/bin/sqlservr & /usr/src/app/import-data.sh
fg