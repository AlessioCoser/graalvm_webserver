package webserver;

import spark.Service;

public class Server implements AutoCloseable {
    private Service service;

    public static Server ignite() {
        return new Server().start();
    }

    public Server start() {
        service = Service.ignite();
        service.port(4545);
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
