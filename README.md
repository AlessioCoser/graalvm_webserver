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
docker build --build-arg VERSION=[build-version] -t webserver .
```

## Run
Run the docker container 
```
docker run --rm -p 4545:4545 -t webserver
```

Run the docker container pointing a specific postgresql database
```
docker run --rm \
    --env DB_HOST=database_host:5432 \
    --env DB_DATABASE=db_name \
    --env DB_USER=another_user \
    --env DB_PASSWORD=another_password \
    -p 4545:4545 -t webserver
```

Run the docker container on different port
```
docker run --rm --env PORT=4321 -p 4321:4321 -t webserver
```

## Try it out
Home:
```
curl http://localhost:4545
```
Healthcheck:
```
curl http://localhost:4545/alive
```
Todo list:
```
curl http://localhost:4545/todos
```