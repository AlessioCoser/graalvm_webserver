package webserver;

import java.sql.Connection;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class Db {
    private final DatabaseConfig config;

    public Db(DatabaseConfig config) {
        this.config = config;
    }

    public Connection connection() throws SQLException {
        return getConnection("jdbc:postgresql://" + config.dbHost() + "/" + config.dbDatabase(), config.dbUser(), config.dbPassword());
    }
}
