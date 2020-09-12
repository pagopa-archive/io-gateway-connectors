# io-sdk-java
Java importer for [IO-SDK](https://github.com/pagopa/io-sdk).  
The connector reads the data from a table called "messages".  
Currently Mysql and Oracle databases are supported.

##Prerequisites
Install [IO-SDK](https://github.com/pagopa/io-sdk/releases)

## Mysql
### Table DDL
```
CREATE TABLE messages (
    id int NOT NULL AUTO_INCREMENT,
    scadenza date NOT NULL,
    destinatario varchar(16) NOT NULL,
    testo varchar(1000) NOT NULL,
    titolo varchar(100) NOT NULL,
    PRIMARY KEY (id)
);
```
### Build IO-SDK action
`iosdk start`  
`make build_mysql`  
`make deploy`

## Oracle
### Table DDL
```
CREATE TABLE messages (
    id NUMBER NOT NULL,
    scadenza date NOT NULL,
    destinatario VARCHAR2(16) NOT NULL,
    testo VARCHAR2(1000) NOT NULL,
    titolo VARCHAR2(100) NOT NULL,
    PRIMARY KEY (id)
);
```
### Build IO-SDK action for Oracle
`iosdk start`  
`make build_oracle`  
`make deploy`

#Development

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
* Create, in the new folder, a [form.json](src/main/resources/mysql/form.json) file with the form configuration for IO-SDK
* Create, in the new folder, a [jdbc.properties](src/main/resources/mysql/jdbc.properties) file with two properties:
  * **jdbc.url** = the jdbc url to connect to the database. You can access the form parameters via the ${paramName} notation.  
    The parameters currently supported are: "host", "port", "database", "sid", "user", "password"  
    The new parameters must be added in the [Args](src/main/java/importer/Args.java) class, and in the "buildJdbcUrl" method 
    of the [JdbcConfiguration](src/main/java/config/JdbcConfiguration.java) class.
  + **jdbc.driver** =  the jdbc driver class
* Create Makefile target:
 ```
build_mysql:
	$(MAKE) DB_TYPE=mysql build
 ```
* Now you can build the action for the new database:  
`make build_mysql`
    
#Release
Run:  
`make snapshot`
