package webserver;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;
import sun.misc.Signal;

public class App {
    static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        log.info("*** Server Configuring");
        Spark.port(4545);
        Spark.get("/", (req, res) -> {
            log.info("*** GET request received with: " + req.pathInfo());
            return "Hello World";
        });
        log.info("*** Server Configured");
        handleStopSignal();
    }

    private static void handleStopSignal() {
        Signal.handle(new Signal("INT"), signal -> {
            log.info("Interrupted by Ctrl+C");
            Spark.stop();
        });
    }
}