package MultiClient;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class WriteThread {
    BufferedWriter bufferedWriter;
    Scanner scanner = new Scanner(System.in);

    WriteThread(final Socket socket, final String name) {
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Thread write = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String send = scanner.nextLine();

                            if (socket.isClosed()) {
                                System.out.println("Chat Box was finished!");
                                break;
                            }

                            bufferedWriter.write(name + " : " + send);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();

                            if (send.equalsIgnoreCase("finish")) {
                                socket.close();
                                break;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            write.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
