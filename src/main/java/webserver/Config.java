package webserver;

public class Config {
    private final Integer port;
    private final String buildVersion;
    private final String dbHost;
    private final String dbUser;
    private final String dbPassword;

    public static Config fromEnv() {
        return new Config(
            Integer.parseInt(env("PORT", "4545")),
            env("BUILD_VERSION", "UNKNOWN"),
            env("DB_HOST", "localhost:5432"),
            env("DB_USER", "user"),
            env("DB_PASSWORD", "password"));
    }

    public Config(Integer port, String buildVersion, String dbHost, String dbUser, String dbPassword) {
        this.port = port;
        this.buildVersion = buildVersion;
        this.dbHost = dbHost;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public Integer port() {
        return port;
    }
    public String version() {
        return buildVersion;
    }
    public String dbHost() {
        return dbHost;
    }
    public String dbUser() {
        return dbUser;
    }
    public String dbPassword() {
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
