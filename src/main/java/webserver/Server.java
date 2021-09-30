package webserver;

import spark.Service;

public class Server implements AutoCloseable {
    private Service service;
    private final Config config;

    public Server(Config config) {
        this.config = config;
    }

    public static Server ignite(Config config) {
        return new Server(config).start();
    }

    public Server start() {
        service = Service.ignite();
        service.port(config.port());
        configureRouting();
        service.awaitInitialization();
        return this;
    }

    private void configureRouting() {
        Db database = new Db(config.dbHost(), config.dbUser(), config.dbPassword());
        service.get("/", (req, res) -> "Hello World");
        service.get("/alive", (req, res) -> "{\"version\":\"" + config.version() + "\"}");
        service.get("/todos", new TodosRoute(database));
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
