# Mower Project Demo

A Maven project for the MowItNow company which has decided to develop an automatic lawn mower for rectangular surfaces.

This project written in Java allows you to move mowers on a lawn by specifying a file as an input with the lawn dimensions, 
the mower position and its instruction sequence. (See below for the data format)

Some edges cases have been taken into account such as :
- A mower going out of bounds stays at the same position ;
- A mower go to a destination where there is another mower ;
- Mowers initially declared outside the lawn are not retained.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for testing purposes.

### Prerequisites
The project requires to be able to use java and mvn line commands :
```
Java 8 (JDK 1.8)
Apache Maven 3 
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [JUnit 5](https://github.com/junit-team/junit5/) - Testing Framework
* [Log4J](https://github.com/apache/log4j) - Logging Services


### Installing

1) First, get a copy of the project by downloading or cloning it.
2) Go to the project folder and package with Apache Maven to get the exectuable JAR (mower-1.0.jar)
3) Run the executable by passing an argument which represents the file to parse.

The file format has to be like the following :
5 5   // the lawn dimensions
1 2 N // the mower position
GAGAGAGAGA // the mower instruction sequence

Two files have been given as an example in the resources folder :
- src/main/resources/dataFile.txt :
- src/main/resources/dataFileWithCollision.txt : contains examples with mower collisions
 
```
cd <path_to_mower_project> 
mvn clean install
java -jar target/mower-1.0.jar <file_to_parse>
```

