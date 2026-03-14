package com.app.quantitymeasurement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectionPool {

    private Queue<Connection> connections = new LinkedList<>();

    public ConnectionPool() {

        try {
            for (int i = 0; i < ApplicationConfig.getPoolSize(); i++) {

                Connection conn = DriverManager.getConnection(
                        ApplicationConfig.getDbUrl(),
                        ApplicationConfig.getUsername(),
                        ApplicationConfig.getPassword());

                connections.add(conn);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error creating connection pool");
        }
    }

    public synchronized Connection getConnection() {

        if (connections.isEmpty())
            throw new RuntimeException("No DB connections available");

        return connections.poll();
    }

    public synchronized void releaseConnection(Connection connection) {
        connections.add(connection);
    }
}
