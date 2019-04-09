package MultiClient;

import java.io.IOException;
import java.net.Socket;

class Client {
    private static String CLIENT_NAME = "Client";

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 3108);
            System.out.println("success");
            new WriteThread(socket, CLIENT_NAME);
            new ReadThread(socket).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}