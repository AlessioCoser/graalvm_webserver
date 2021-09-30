FROM ghcr.io/graalvm/graalvm-ce:21.2.0 AS build
WORKDIR /build
COPY ./build/libs/native_web_server-all.jar app.jar
RUN gu install native-image
RUN native-image \
    --static \
    --enable-http \
    --no-fallback \
    --initialize-at-build-time=org.eclipse.jetty,org.slf4j,javax.servlet,org.sparkjava \
    -cp app.jar \
    -H:Name=app \
    -H:Class=webserver.App \
    -H:+ReportUnsupportedElementsAtRuntime \
    -H:+ReportExceptionStackTraces

FROM scratch
ARG VERSION
ENV BUILD_VERSION=$VERSION
COPY --from=build /build/app /app
CMD ["/app"]