package webserver;

import spark.Service;

public class Server implements AutoCloseable {
    private Service service;
    private final Integer port;

    public Server(Integer port) {
        this.port = port;
    }

    public static Server ignite(Integer port) {
        return new Server(port).start();
    }

    public Server start() {
        service = Service.ignite();
        service.port(port);
        configureRouting();
        service.awaitInitialization();
        return this;
    }

    private void configureRouting() {
        service.get("/", (req, res) -> "Hello World");
    }

    public void stop() {
        service.stop();
        service.awaitStop();
    }

    @Override
    public void close() throws Exception {
        stop();
    }
}
