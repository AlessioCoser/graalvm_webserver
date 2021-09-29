# GraalVm WebServer with Spark Java on Docker 

A simple implementation of a [Jetty Web Server](https://www.eclipse.org/jetty/) with [Spark Java](http://sparkjava.com/) and [GraalVm](https://www.graalvm.org/) on [Docker](https://www.docker.com/) container:
1. **:rocket: blazingly fast (~4ms server startup)**
2. **:leafy_green: very lightweight (~30MB Docker image)**

## Prerequisites
- Java 11
- Docker

## Build
Build the application and the docker image
```
./gradlew clean build
```
```
docker build -t webserver .
```

## Run
Run the docker container 
```
docker run --rm -p 4545:4545 -t webserver
```

## Try it out
```
curl http://localhost:4545
```