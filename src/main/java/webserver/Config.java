package webserver;

interface ServerConfig {
    int port();
    String version();
}

interface DatabaseConfig {
    String dbHost();
    String dbDatabase();
    String dbUser();
    String dbPassword();
}

public class Config implements ServerConfig, DatabaseConfig {
    private final int port;
    private final String buildVersion;
    private final String dbHost;
    private final String dbDatabase;
    private final String dbUser;
    private final String dbPassword;

    public static Config fromEnv() {
        return new Config(
            Integer.parseInt(env("PORT", "4545")),
            env("BUILD_VERSION", "UNKNOWN"),
            env("DB_HOST", "localhost:5432"),
            env("DB_DATABASE", "tests"),
            env("DB_USER", "user"),
            env("DB_PASSWORD", "password"));
    }

    public Config(int port, String buildVersion, String dbHost, String dbDatabase, String dbUser, String dbPassword) {
        this.port = port;
        this.buildVersion = buildVersion;
        this.dbHost = dbHost;
        this.dbDatabase = dbDatabase;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    @Override public int port() {
        return port;
    }
    @Override public String version() {
        return buildVersion;
    }
    @Override public String dbHost() {
        return dbHost;
    }
    @Override public String dbDatabase() {
        return dbDatabase;
    }
    @Override public String dbUser() {
        return dbUser;
    }
    @Override public String dbPassword() {
        return dbPassword;
    }

    private static String env(String name, String defaultValue) {
        String env = System.getenv(name);
        if(env == null) {
            return defaultValue;
        }
        return env;
    }
}
