package ServerClient;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

class BoxChat {
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;


    BoxChat(final Socket socket,final String name) {
        Scanner scanner = new Scanner(System.in);
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            Thread read = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            if(socket.isClosed()){
                                break;
                            }
                            String data = bufferedReader.readLine();
                            if (!data.isEmpty()) {
                                if (data.equalsIgnoreCase("finish")){
                                    socket.close();
                                    break;
                                } else {
                                    System.out.println(name + " : " + data);
                                }
                            }

                        } catch (IOException e) {
                            if(!socket.isClosed()) {
                                try {
                                    socket.close();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    }
                }
            });

            read.start();
            while (true) {
                try {

                    String send = scanner.nextLine();

                    if(socket.isClosed()){
                        System.out.println("Chat Box was finished!");
                        break;
                    }
                    bufferedWriter.write(send);
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
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}