# Fontys JEA application (SongWiki)

## :hammer_and_pick: Tools
The following tools are used for the implementation of SongWiki:

- Arquillian (integration testing framework)
- Payara (application server)
- Maven shrinkwrap resolver (only including necessary code during integration tests)
- Jersey (RESTful Web Services framework with JAX-RS API implementation)
- Hibernate (ORM tool)
- Java EE
- GSON (easy JSON serialization/deserialization)
- Maven Cargo (maven deployments of application server)

## :wrench: Configuration application server
This project uses Payara as application server.  
As Payara is based on Glassfish a lot of configuration is interchangeable.

### :zap: Application
The current setup for running the application looks like the following:

Resources -> JDBC -> JDBC Resources:
* JNDI name: jdbc/JEAPostgreSQLPool

Resources -> JDBC -> JDBC Connection Pools:
- General:
    * Pool name: JEAPostgreSQLPool
    * Resource type: javax.sql.ConnectionPoolDataSource
    * Datasource Classname: org.postgresql.ds.PGConnectionPoolDataSource
    * Additional default settings
    
- Additional Properties:
    * password: your_database_password_here
    * serverName: localhost
    * user: postgres
    * databaseName: postgres

### :heavy_check_mark: Testing
This application is setup to have a managed Payara Server running when tests are executed.
A production environment is mimicked as much as possible.

Arquillian is used for the integration tests on the managed server as it's the most widely used and battle-tested 
integration testing framework. Its configuration can be found in the `test/resources/` directory.

The Payara server is set up to use a different connection pool, connection resource and database for testing.  
Doing this makes sure the tests won't interfere with the production environment and can be tweaked when necessary.  
These are the settings I use:

Resources -> JDBC -> JDBC Resources:
* JNDI name: jdbc/JEAPostgreSQLTestPool

Resources -> JDBC -> JDBC Connection Pools:
- General:
    * Pool name: JEAPostgreSQLTestPool
    * Resource type: javax.sql.ConnectionPoolDataSource
    * Datasource Classname: org.postgresql.ds.PGConnectionPoolDataSource
    * Additional default settings
    
- Additional Properties:
    * password: your_database_password_here
    * serverName: localhost
    * user: postgres
    * databaseName: postgres_test

:warning: take note of the arquillian.xml file in `test/resources/`.  
The `glassFishHome` property should be set to the local copy of Payara to run the tests with!  
Currenty, the Payara server is expected to live one directory above the source code (denoted by the `../`).  
If this is not set correctly the application won't find the server to be deployed to and therefor won't run!

## :round_pushpin: Endpoint must-knowns
Endpoint classes need to manually throw exceptions when an error occurs.  
If not thrown, Jersey returns status codes in the 200 range.  
For example, 204 is returned when an object is null instead of the expected 404.  
[Reference](https://stackoverflow.com/a/22869076)