## Simple Spring Boot based RESTful API whose purpose is to offer an endpoint to connect to OpenStreetMap in order to retrieve coordinates based on OSM elements (node, way relation).


### Prerequisites

Make sure you have Maven (I am using v3.3.9) and Java 1.8 SDK installed on your machine.


### How to build the application

Navigate to the project folder and run:
```bash
mvn clean package
```


### How to run the application

Navigate to the project folder and run:
```bash
java -jar .\target\ngx-leaflet-poc-backend-1.0.0.jar
```


### Notes

20 records are stored in the in-memory database at application startup: they represent 20 Italian cities.

Unit tests run against the production database (instead of the test one).

No specific error-handling strategy is in place, a lot more could be done if this were a production application.