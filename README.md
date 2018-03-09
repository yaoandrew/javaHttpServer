## Java based HTTP Server :rocket:

### Dependencies
* [Java 1.8.0_152](http://docs.orcle.com/javase/8/docs/)
* [Maven 3.5.2](https://maven.apache.org/)
* [JUnit 4.12](http://junit.org/junit4/)

### Setup
You can get the latest release of the server running ```git clone```
Run the tests with ```mvn test``` or do it all with ```mvn package```

If everything looks good, give it a whirl....

### Running the server
You can start the server from the command line with ```java -jar target/httpServer-1.0-SNAPSHOT.jar``` from the project home directory.

By default it will run on port 5000 and will point to the USER_HOME/public. 

You can specify a port by using ```-p [port number]``` and directory ```-d [full path to directory]```
