package utils;

import org.testcontainers.containers.FixedHostPortGenericContainer;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;

import static org.testcontainers.utility.MountableFile.forClasspathResource;

public class ContainerPostgres extends FixedHostPortGenericContainer {
    private final Integer port;
    private final String user;
    private final String password;

    public ContainerPostgres() {
        this(5431, "user", "password");
    }

    public ContainerPostgres(Integer port, String user, String password) {
        super("postgres:11");
        this.port = port;
        this.user = user;
        this.password = password;
        withEnv("POSTGRES_USER", user);
        withEnv("POSTGRES_PASSWORD", password);
        withCopyFileToContainer(forClasspathResource("db"), "/docker-entrypoint-initdb.d");
        withFixedExposedPort(port, 5432);
        withReuse(true);
        waitingFor(defaultWaitStrategy());
    }

    public String host() {
        return "localhost:" + port;
    }

    public String user() {
        return user;
    }

    public String password() {
        return password;
    }

    private static LogMessageWaitStrategy defaultWaitStrategy() {
        return new LogMessageWaitStrategy()
                .withRegEx(".*database system is ready to accept connections.*\\s")
                .withTimes(2);
    }
}
