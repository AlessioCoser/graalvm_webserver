package webserver;


import spark.Spark;
import sun.misc.Signal;

public class App {
    public static void main(String[] args) {
        Spark.port(4545);
        Spark.get("/", (req, res) -> "Hello World");
        handleStopSignal();
    }

    private static void handleStopSignal() {
        Signal.handle(new Signal("INT"), signal -> {
            System.out.println("Interrupted by Ctrl+C");
            Spark.stop();
        });
    }
}