# GraalVm WebServer with Spark Java on Docker 

### Build the application
```
./gradlew clean build
```

### Build the docker image
```
docker build -t webserver .
```

### Run the docker image
```
docker run --rm -p 4545:4545 -t webserver
```

### Build and run together
```
./gradlew clean build && docker build -t webserver . && docker run --rm -p 4545:4545 -t webserver
```