# store-rate-api

Store fee api example

## Build the project

Run the following command inside projects root directory

    mvn clean install
    
Requirements:

* Java 11
* Maven

## Run the project

Run the following commnad inside target directory

    java -jar store-rate-api-0.0.1-SNAPSHOT.jar
    
Now type the following url

    http://localhost:8080/
    
It should appear a message like "Whitelabel Error Page"

## Browse the database

When active you can browse database from your web browser with this URL

    localhost:8080/h2-console
    
Use connection data provided in application.properties file