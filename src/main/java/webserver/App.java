package webserver;


import spark.Spark;

public class App {
    public static void main(String[] args) {
        Spark.port(4545);
        Spark.get("/", (req, res) -> "Hello World");
    }
}