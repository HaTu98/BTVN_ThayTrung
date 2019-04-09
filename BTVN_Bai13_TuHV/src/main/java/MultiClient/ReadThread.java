package MultiClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread {
    BufferedReader bufferedReader;
    Socket socket;

    ReadThread(Socket socket) {
        this.socket = socket;

    }

    @Override
    public void run() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while (true) {
                        try {
                            if (socket.isClosed()) {
                                break;
                            }
                            String data = bufferedReader.readLine();
                            if (!data.isEmpty()) {
                                if (data.equalsIgnoreCase("finish")) {
                                    socket.close();
                                    break;
                                } else {
                                    System.out.println(data);
                                }
                            }

                        } catch (IOException e) {
                            if (!socket.isClosed()) {
                                try {
                                    socket.close();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
