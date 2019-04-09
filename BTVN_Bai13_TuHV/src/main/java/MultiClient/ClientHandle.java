package MultiClient;

import java.io.*;
import java.net.Socket;

public class ClientHandle implements Runnable {
    BufferedWriter bufferedWriter;
    BufferedReader bufferedReader;
    Socket socket;

    ClientHandle(Socket socket, BufferedWriter bufferedWriter) {
        this.socket = socket;
        this.bufferedWriter = bufferedWriter;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String msg;
        try {
            while ((msg = bufferedReader.readLine()) != null) {
                System.out.println(msg);
                bufferedWriter.write(msg);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
