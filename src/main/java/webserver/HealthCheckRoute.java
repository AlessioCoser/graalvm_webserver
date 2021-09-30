package webserver;

import spark.Request;
import spark.Response;
import spark.Route;

public class HealthCheckRoute implements Route {
    private final ServerConfig config;

    public HealthCheckRoute(ServerConfig config) {
        this.config = config;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        return "{\"version\":\"" + config.version() + "\"}";
    }
}
