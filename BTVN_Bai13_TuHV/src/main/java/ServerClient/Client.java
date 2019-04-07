package  ServerClient;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;


public class Client {
    public static void main(String[] args) {

        try {
            Socket socketClient = new Socket("localhost",8880);
            System.out.println("Connected Server !");
            System.out.println("=============  CHAT ===========");
            new BoxChat(socketClient, "Server");
        } catch (ConnectException ce){
            System.out.println("Not Found Server !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}