# User Authentication

## Description
This micro service is responsible for User authentication

## Requirements

* [Java](https://www.oracle.com/java/)
* [Maven](https://maven.apache.org/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [jUnit](https://junit.org/)
* [mockito](https://site.mockito.org/)
* [Docker](https://www.docker.com/)



## Installation

Use the maven command line interface [Maven](https://maven.apache.org/) to install the dependencies

```bash
mvn install -DSkipTests
```

## Test
Use the maven command line interface [Maven](https://maven.apache.org/) to perform unit test
```bash
mvn test
```
## package build 
```bash
mvn package
```
## Docker build
```bash
docker build --rm -f "Dockerfile" -t user-authentication:latest "."
```

### How the docker image is build
* Base image for the docker container is `openjdk:11-jdk`

* Compiled class files under `target/user-authentication-1.0.1.0-SNAPSHOT.jar ` are copied to `webapp.jar` in the image

### How to run docker container

```bash
docker container run  -p 8761:8761 -d --rm --name user-authentication  user-authentication:latest
```
 

## Health Check

+ 
    - path : /user-authentication/health
    - port : 8761
    

## Swagger url

  *  [SWAGGER](http://localhost:8761/swagger-ui/index.html)


