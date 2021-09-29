package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Service;

public class Server implements AutoCloseable {
    private final Logger log = LoggerFactory.getLogger(Server.class);
    private Service service;

    public Server start() {
        log.info("*** Starting the server");
        service = Service.ignite();
        service.port(4545);
        configureRouting();
        service.awaitInitialization();
        log.info("*** Server started");
        return this;
    }

    public void stop() {
        service.stop();
        service.awaitStop();
    }

    private void configureRouting() {
        service.get("/", (req, res) -> {
            log.info("*** GET request received with: " + req.pathInfo());
            return "Hello World";
        });
    }

    @Override
    public void close() throws Exception {
        stop();
    }
}
