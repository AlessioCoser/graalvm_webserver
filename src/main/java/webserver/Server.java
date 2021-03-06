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
        Db database = new Db(config);
        TodosRepository todosRepository = new TodosRepository(database);

        service.get("/", (req, res) -> "Hello World");
        service.get("/alive", new HealthCheckRoute(config));
        service.get("/todos", new TodosRoute(todosRepository));
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
