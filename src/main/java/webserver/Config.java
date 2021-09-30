package webserver;

public class Config {
    private final Integer port;
    private final String buildVersion;

    public static Config fromEnv() {
        return new Config(
            Integer.parseInt(env("PORT", "4545")),
            env("BUILD_VERSION", "UNKNOWN")
        );
    }

    public Config(Integer port, String buildVersion) {
        this.port = port;
        this.buildVersion = buildVersion;
    }

    public Integer port() {
        return port;
    }
    public String version() {
        return buildVersion;
    }

    private static String env(String name, String defaultValue) {
        String env = System.getenv(name);
        if(env == null) {
            return defaultValue;
        }
        return env;
    }
}
