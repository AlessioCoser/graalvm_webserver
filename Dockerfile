FROM ghcr.io/graalvm/graalvm-ce:21.2.0
WORKDIR /test
COPY ./build/libs/native_web_server.jar app.jar
RUN gu install native-image
RUN native-image --no-fallback -cp app.jar -H:Name=native_web_server -H:Class=webserver.App -H:+ReportUnsupportedElementsAtRuntime
RUN ls -la
CMD 'bash'