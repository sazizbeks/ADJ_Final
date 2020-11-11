package kz.edu.astanait.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Postgres implements IDB{
    private Connection connection;

    @Override
    public Connection getConnection() {
        if(connection==null){
            try {
                connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/ADJ_Final", "postgres", "3418533");
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        return connection;
    }
}
