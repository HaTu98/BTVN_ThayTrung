package connectionpool;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserConnectionPool {
    private static final String DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbcdemo";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "1234";

    public static void main(String[] args) {
        System.out.println("start");
        final ConnectionPool connectionPool = new ConnectionPool(DB_DRIVER_CLASS, DB_URL, DB_USERNAME, DB_PASSWORD);

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    Connection connection = null;
                    try {
                        connection = connectionPool.getConnectionPool();
                        select(connection);
                        connectionPool.releaseConnection(connection);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        System.out.println("finish");
    }

    public static void select(Connection connection) {
        try {
            CallableStatement call = connection.prepareCall("{call selectUser()}");

            ResultSet resultSet = call.executeQuery();

            while (resultSet.next()) {
                System.out.println("Id : " + resultSet.getInt(1) + "\t" + "name : " + resultSet.getString(2)
                        + "\t" + "username : " + resultSet.getString(3) + "\t" + "password : " + resultSet.getString(4)
                        + "\t" + "email : " + resultSet.getString(5)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
