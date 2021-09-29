package webserver;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Signal;

public class App {
    static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        Server server = new Server(log);
        handleStopSignal(server);
        server.start();
    }

    private static void handleStopSignal(Server server) {
        Signal.handle(new Signal("INT"), signal -> {
            log.info("Interrupted by Ctrl+C");
            server.stop();
        });
    }
}