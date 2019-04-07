package  ServerClient;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        System.out.print("IP server : ");
        String serverHost;
        while(true) {
            serverHost = sc.nextLine();
            if(serverHost.equals("localhost")){
                break;
            } else {
                System.out.println("Not found! Retry : ");
            }
        }
        try {
            Socket socketClient = new Socket(serverHost,8880);
            System.out.println("Connected Server !");
            System.out.println("=============  CHAT ===========");
            new BoxChat(socketClient, "Server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}