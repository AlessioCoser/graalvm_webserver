FROM ghcr.io/graalvm/graalvm-ce:21.0.0 AS build
WORKDIR /build
COPY ./build/libs/native_web_server-all.jar app.jar
COPY ./reflection.json reflection.json
RUN gu install native-image
RUN native-image \
    --static \
    --enable-http \
    --enable-https \
    --no-fallback \
    -cp app.jar \
    -H:ReflectionConfigurationFiles=reflection.json \
    -H:Name=app \
    -H:Class=webserver.App

FROM scratch
COPY --from=build /build/app /app
CMD ["/app"]