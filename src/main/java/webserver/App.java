package webserver;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Signal;

public class App {
    static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        Server server = Server.ignite(Config.fromEnv());
        handleStopSignal(server);
    }

    private static void handleStopSignal(Server server) {
        Signal.handle(new Signal("INT"), signal -> {
            log.info("Interrupted by Ctrl+C");
            server.stop();
        });
    }
}