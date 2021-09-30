package webserver;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import utils.ContainerPostgres;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.net.http.HttpResponse.BodyHandlers.ofString;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class ServerWithDbTest {
    @Container
    public static ContainerPostgres postgres = new ContainerPostgres();
    private final Db db = new Db(postgres.host(), postgres.user(), postgres.password());

    @Test
    public void todosPathShouldRespondWithAListOfSavedTodos() throws Exception {
        addTodo("first");
        addTodo("second");
        try (Server server = Server.ignite(configFrom(postgres))) {
            assertEquals("[\"first\",\"second\"]", get("http://localhost:4321/todos").body());
        }
    }

    private void addTodo(String text) throws SQLException {
        PreparedStatement statement = this.db.connection()
                .prepareStatement("INSERT INTO public.todos(text) VALUES (?);");
        statement.setString(1, text);
        statement.executeUpdate();
    }

    private Config configFrom(ContainerPostgres postgres) {
        return new Config(4321, null, postgres.host(), postgres.user(), postgres.password());
    }

    private HttpResponse<String> get(String url) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder(URI.create(url)).build();
        return HttpClient.newHttpClient().send(request, ofString());
    }
}

