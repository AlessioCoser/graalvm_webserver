package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

public class TodosRepository {
    private final Logger log = LoggerFactory.getLogger(TodosRepository.class);
    private final Db database;

    public TodosRepository(Db database) {
        this.database = database;
    }

    public List<String> all() {
        try {
            List<String> todos = new ArrayList<>();
            PreparedStatement statement = database.connection().prepareStatement("select * from public.todos");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                todos.add(resultSet.getString("text"));
            }
            return todos;
        } catch (Exception e) {
            log.error("Cannot retrieve all todos", e);
            return emptyList();
        }
    }
}
