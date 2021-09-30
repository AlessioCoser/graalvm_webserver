package webserver;

import java.sql.Connection;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class Db {
    private String host;
    private String user;
    private String password;

    public Db(String host, String user, String password) {
        this.host = host;
        this.user = user;
        this.password = password;
    }

    public Connection connection() throws SQLException {
        return getConnection("jdbc:postgresql://" + host + "/tests", user, password);
    }
}
