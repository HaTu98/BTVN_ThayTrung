package connection;

import java.sql.Connection;

public class JDBCStoredProcedure {

    public static void main(String[] args) {
        Query.insert("Ha Van TU", "hatu", "123456", "hatu98nd@gmail.com");
        Query.select();
    }


}
