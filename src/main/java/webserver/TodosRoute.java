package webserver;

import spark.Request;
import spark.Response;
import spark.Route;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodosRoute implements Route {
    private Db database;

    public TodosRoute(Db database) {
        this.database = database;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        return toString(allTodos());
    }

    private String toString(List<String> list) {
        if(list.isEmpty()) {
            return "[]";
        }
        return "[\"" + String.join("\",\"", list) + "\"]";
    }

    private List<String> allTodos() throws SQLException {
        List<String> todos = new ArrayList<>();
        PreparedStatement statement = database.connection().prepareStatement("select * from public.todos");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            todos.add(resultSet.getString("text"));
        }
        return todos;
    }
}
