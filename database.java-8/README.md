# Database Java 8 Connector
Java importer for [IO-Gateway](https://github.com/pagopa/io-gateway).  
The connector reads the data from a table called "messages".  
Currently Mysql and Oracle databases are supported.

# Prerequisites
Install [IO-Gateway](https://github.com/pagopa/io-gateway)

## Mysql

Compatible with versions 5.6, 5.7, 8.0.

### Table DDL
```
CREATE TABLE messages (
    id int NOT NULL AUTO_INCREMENT,
    amount bigint,
    due_date date NOT NULL,
    fiscal_code varchar(16) NOT NULL,
    invalid_after_due_date boolean NOT NULL,
    markdown varchar(10000) NOT NULL,
    notice_number varchar(18) NOT NULL,
    subject varchar(100) NOT NULL,
    PRIMARY KEY (id)
);
```
### Build IO-Gateway connector
`iogw start`  
`make build_mysql`  
`make deploy`

## Oracle

Compatible with versions 19.x, 18.3, 12.2, 12.1, 11.2.0.4

### Table DDL
```
CREATE TABLE messages (
    id number NOT NULL,
    amount number,
    due_date date NOT NULL,
    fiscal_code VARCHAR2(16) NOT NULL,
    invalid_after_due_date number(1) NOT NULL,
    markdown clob NOT NULL,
    notice_number VARCHAR2(18) NOT NULL,
    subject VARCHAR2(100) NOT NULL,
    PRIMARY KEY (id)
);
```
### Build IO-Gateway connector for Oracle
`iogw start`  
`make build_oracle`  
`make deploy`


## SQL Server

Compatible with versions SQL Server 2019, SQL Server 2017, SQL Server 2016, SQL Server 2014, SQL Server 2012.

### Table DDL
```
CREATE TABLE messages (
    id int NOT NULL IDENTITY,
    amount bigint,
    due_date date NOT NULL,
    fiscal_code varchar(16) NOT NULL,
    invalid_after_due_date bit NOT NULL,
    markdown text NOT NULL,
    notice_number varchar(18) NOT NULL,
    subject varchar(100) NOT NULL,
    PRIMARY KEY (id)
);
```
### Build IO-Gateway action for SQL Server
`iogw start`  
`make build_sqlserver`  
`make deploy`

## PostgreSQL

Compatible with versions 8.2 and higher.

### Table DDL
```
CREATE TABLE messages (
    id SERIAL NOT NULL,
    amount bigint,
    due_date date NOT NULL,
    fiscal_code varchar(16) NOT NULL,
    invalid_after_due_date boolean NOT NULL,
    markdown varchar(10000) NOT NULL,
    notice_number varchar(18) NOT NULL,
    subject varchar(100) NOT NULL,
    PRIMARY KEY (id)
);
```
### Build IO-Gateway action for PostgreSQL
`iogw start`  
`make build_postgresql`  
`make deploy`

# Development

## How to - Run test
* If docker is accessible on localhost:    
`./test.sh` 
* otherwise the docker ip must be specified  
`./test.sh 192.168.1.150`

## How to - Add new database
The connection via database is made using JDBC drivers.   
Only the database driver chosen during build is included in the final jar.

* Choose an id for the database you want to add. Example: mysql 
* Add a new configuration in [build.gradle](build.gradle)
 ```
configurations {
    mysql
}
```
* Add jdbc driver in [build.gradle](build.gradle)
```
dependencies {
    mysql 'mysql:mysql-connector-java:8.0.20'

    testImplementation 'mysql:mysql-connector-java:8.0.20'
}
```
* Add a new folder in "src/main/resources" by naming it with the database id. Example: "src/main/resources/mysql"
* Create, in the new folder, a [form.json](src/main/resources/mysql/form.json) file with the form configuration for IO-Gateway
* Create, in the new folder, a [jdbc.properties](src/main/resources/mysql/jdbc.properties) file with two properties:
  * **jdbc.url** = the jdbc url to connect to the database. You can access the form parameters via the ${paramName} notation.  
    The parameters currently supported are: "host", "port", "database", "sid", "user", "password"  
    The new parameters must be added in the [Args](src/main/java/importer/Args.java) class, and in the "buildJdbcUrl" method 
    of the [JdbcConfiguration](src/main/java/config/JdbcConfiguration.java) class.
  + **jdbc.driver** =  the jdbc driver class
* Configure required parameters in the method "showForm" of [Importer](src/main/java/importer/Importer.java)
* Create Makefile targets:
 ```
build_mysql:
	$(MAKE) DB_TYPE=mysql build
release_mysql:
	$(MAKE) DB_TYPE=mysql release
 ```
* Now you can build the action for the new database:  
`make build_mysql`
    
# Release
Run:  
`make snapshot`
