package MultiClient;

import java.io.IOException;
import java.net.Socket;

class Client1 {
    private static String CLIEN_NAME = "Client 1";

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 3108);
            System.out.println("success");
            new WriteThread(socket, CLIEN_NAME);
            new ReadThread(socket).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}