package connection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {
    static Connection connection;
    static CallableStatement call = null;
    static ResultSet resultSet = null;


    public static void select(){
        try{
            connection = DBConnection.getConnection();
            call = connection.prepareCall("{call selectUser()}");

            resultSet = call.executeQuery();

            while(resultSet.next()){
                System.out.println("Id : " + resultSet.getInt(1)+"\t" + "name : " + resultSet.getString(2)
                       +"\t" + "username : " + resultSet.getString(3)+"\t" + "password : " + resultSet.getString(4)
                        +"\t" + "email : " + resultSet.getString(5)
                );
            }

            System.out.println("success");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                if(call != null) {
                    call.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insert(String name, String username, String password, String email){
        try{
            connection = DBConnection.getConnection();
            call = connection.prepareCall("{call insertUser(?,?,?,?)}");
            call.setString(1,name);
            call.setString(2,username);
            call.setString(3,password);
            call.setString(4,email);

            call.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                if(call != null) {
                    call.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
