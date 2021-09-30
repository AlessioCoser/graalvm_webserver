package webserver;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpResponse.BodyHandlers.ofString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerTest {
    @Test
    public void homePathShouldRespondWithHelloWorld() throws Exception {
        try (Server server = Server.ignite(config(4321, null))) {
            assertEquals("Hello World", get("http://localhost:4321/").body());
        }
    }

    @Test
    public void healthcheckShouldReturnTheCurrentVersion() throws Exception {
        try (Server server = Server.ignite(config(4321, "1.0.0"))) {
            assertEquals("{\"version\":\"1.0.0\"}", get("http://localhost:4321/alive").body());
        }
    }

    @NotNull
    private Config config(int port, String version) {
        return new Config(port, version, null, null, null);
    }

    private HttpResponse<String> get(String url) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder(URI.create(url)).build();
        return HttpClient.newHttpClient().send(request, ofString());
    }
}

