# store-rate-api

Store fee api example

## Build the project

Run the following command inside projects root directory

    mvn clean install
    
Requirements:

* Java 11
* Maven

## Run the project

### As JAR file

Run the following commnad inside target directory

    java -jar store-rate-api-0.0.1-SNAPSHOT.jar
    
Now type the following url

    http://localhost:8080/actuator/health

### As a Docker image
    
Requirements:
* Docker

Build the image with the following command

    docker build -t store-rate-api:1.0.1 .
    
Now run with the following command

    docker run -p 8080:8080 --rm store-rate-api:1.0.1
    
Try it with the following URL

    http://localhost:8080/actuator/health
    
#### Healthcheck

This Docker image has a healthcheck statement.

We can view the status of our (and other) services running this command:

    docker ps
    
It will be useful for:
* Service status monitoring
* Error recovery
* Dependant services running
* ...

## Browse the database

When active you can browse database from your web browser with this URL

    localhost:8080/h2-console
    
Use connection data provided in application.properties file

## Browse the API Documentation

Explore the following URL

    http://localhost:8080/swagger-ui.html