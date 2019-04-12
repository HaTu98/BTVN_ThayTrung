package connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private String driver, url, username, password;
    private final int POOL_Max_SIZE = 5;
    private List<Connection> initializeConnectionPool;
    private List<Connection> userConnectionPool;

    public ConnectionPool(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
        initializeConnectionPool = new ArrayList<Connection>();
        userConnectionPool = new ArrayList<Connection>();
        intializeConnection();
    }

    private void intializeConnection() {
        while (initializeConnectionPool.size() < POOL_Max_SIZE) {
            initializeConnectionPool.add(createNewConnection());
        }
    }

    private Connection createNewConnection() {
        try {
            Class.forName(driver);
            return (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized Connection getConnectionPool() throws SQLException {
        Connection connection = null;
        if (initializeConnectionPool.isEmpty()) {
            if (userConnectionPool.size() < POOL_Max_SIZE) {
                initializeConnectionPool.add(createNewConnection());
                connection = initializeConnectionPool.remove(0);
                userConnectionPool.add(connection);
                return connection;
            } else {
                try {
                    System.out.println("Connection limit reached. Waiting...");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        while(true) {
            if(initializeConnectionPool.size() > 0) {
                connection = initializeConnectionPool.remove(0);
                userConnectionPool.add(connection);
                break;
            }else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return connection;
    }

    public synchronized void releaseConnection(Connection connection) {
        initializeConnectionPool.add(connection);
        userConnectionPool.remove(connection);
        notifyAll();
    }

    private void closeConnections(List<Connection> connections) {
        try {
            for (int i = 0; i < connections.size(); i++) {
                Connection connection = connections.get(i);
                if (!connection.isClosed()) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void closeAllConnection() {
        closeConnections(initializeConnectionPool);
        initializeConnectionPool = new ArrayList<Connection>();
        closeConnections(userConnectionPool);
        userConnectionPool = new ArrayList<Connection>();
    }

}
