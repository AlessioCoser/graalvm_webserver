package webserver;

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
        try (Server server = Server.ignite(4545)) {
            assertEquals("Hello World", get("http://localhost:4545/").body());
        }
    }

    private HttpResponse<String> get(String url) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder(URI.create(url)).build();
        return HttpClient.newHttpClient().send(request, ofString());
    }
}

