package webserver;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Service;
import spark.Spark;
import sun.misc.Signal;

public class App {
    static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        log.info("*** Server Configuring");
        Service webService = Service.ignite();
        webService.port(4545);
        webService.get("/", (req, res) -> {
            log.info("*** GET request received with: " + req.pathInfo());
            return "Hello World";
        });
        log.info("*** Server Configured");
        handleStopSignal(webService);
    }

    private static void handleStopSignal(Service webService) {
        Signal.handle(new Signal("INT"), signal -> {
            log.info("Interrupted by Ctrl+C");
            webService.stop();
            webService.awaitStop();
        });
    }
}