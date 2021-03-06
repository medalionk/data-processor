# Data Processor

Sample application to read rss feed from given url link every x seconds and perform minimum data processing.
The processed data is saved in a in-memory database (H2) and return the last 10 entries in JSON format.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

* [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Maven 3+](https://maven.apache.org/download.cgi)


### Configurations

There is the options to configure the RSS Feed url link and the time interval between fetching the feeds

```
rss-feed-url={rss feeds url}
feed-delay={time in seconds}

```

The listening port can also be configured

```
server.port={port number}

```

### Building the application

Make sure that Maven is installed and is in your path.
To build the application, navigate to the project folder and run the command as shown below:

```

mvn clean
mvn package

```

To build the application, while skipping tests, run the command as shown below:

```

mvn clean
mvn package -Dmaven.test.skip=true

```

### Running

After creating the executable jar, run the application using java -jar, as shown below:

```

java -jar target/data-processor-0.0.1-SNAPSHOT.jar

```


## Running the tests
     
To run only test, navigate to the project folder and run the command as shown below: 
```

mvn tests

```

## Deployment

Copy the executable jar to the server and run using a Process Control System application such as [Supervisord](http://supervisord.org/) or using java -jar 


## API Reference

Visit the homepage of the running application to view the Swagger API documentation.

## Built With

* [Spring Boot](https://projects.spring.io/spring-boot/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Lombok](https://projectlombok.org/) - Automatic generation of getters, setters, equals, hashCode and toString
* [MapStruct](http://mapstruct.org/) - Code generator for mappings between Java bean types.
* [REST Assured](http://rest-assured.io/) - Testing and validating REST services.
* [Swagger](https://swagger.io/) - API documentation.
* [ROME](https://rometools.github.io/rome/) - Java framework for RSS and Atom feeds.
* [Apache Commons](https://github.com/ReactiveX/RxJava) - Apache project focused on all aspects of reusable Java components..


## Contributing


## Versioning

API versioning currently at version 1.0


## Author

* **Bilal Abdullah** - [medalionk](https://github.com/medalionk)


## License

This project is licensed under the Apache License - see the [LICENSE](LICENSE) file for details

## Acknowledgments