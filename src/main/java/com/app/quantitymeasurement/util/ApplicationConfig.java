package com.app.quantitymeasurement.util;

import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {

    private static Properties properties = new Properties();

    static {
        try {
            InputStream input = ApplicationConfig.class
                    .getClassLoader()
                    .getResourceAsStream("application.properties");

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load application properties");
        }
    }

    public static String getDbUrl() {
        return properties.getProperty("db.url");
    }

    public static String getUsername() {
        return properties.getProperty("db.username");
    }

    public static String getPassword() {
        return properties.getProperty("db.password");
    }

    public static int getPoolSize() {
        return Integer.parseInt(properties.getProperty("db.pool.size"));
    }

    public static String getRepositoryType() {
        return properties.getProperty("repository.type");
    }
}
