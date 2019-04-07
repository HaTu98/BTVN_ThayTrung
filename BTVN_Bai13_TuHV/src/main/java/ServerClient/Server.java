package ServerClient;

import java.io.*;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static void main(String[] args) {
        ServerSocket listener = null;
        try {
            listener = new ServerSocket(8880);
            Socket socketServer = listener.accept();
            System.out.println("connect !");
            System.out.println("=============  CHAT ===========");
            new BoxChat(socketServer, "Client");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (listener != null) {
                try {
                    listener.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}