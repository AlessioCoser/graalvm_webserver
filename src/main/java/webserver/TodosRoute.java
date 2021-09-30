package webserver;

import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class TodosRoute implements Route {
    private final TodosRepository repository;

    public TodosRoute(TodosRepository repository) {
        this.repository = repository;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        return toString(repository.all());
    }

    private String toString(List<String> list) {
        if(list.isEmpty()) {
            return "[]";
        }
        return "[\"" + String.join("\",\"", list) + "\"]";
    }
}
