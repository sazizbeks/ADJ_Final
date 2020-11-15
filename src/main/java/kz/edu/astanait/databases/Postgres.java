package kz.edu.astanait.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Postgres {
    private static Connection connection;

    private Postgres() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ADJ_Final", "postgres", "3418533");
            } catch (SQLException | ClassNotFoundException throwable) {
                throwable.printStackTrace();
            }
        }

        return connection;
    }
}
