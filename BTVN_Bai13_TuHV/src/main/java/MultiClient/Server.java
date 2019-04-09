package MultiClient;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

class Server {
    static final int port = 3108;
    static ServerSocket serverSocket;
    static ArrayList<BufferedWriter> arr = new ArrayList<BufferedWriter>();
    static ArrayList<Socket> sockets = new ArrayList<Socket>();
    static BufferedWriter bufferedWriter;
    static BufferedReader bufferedReader;

    public static void main(String[] args) {
        // ExecutorService pool = Executors.newFixedThreadPool(5);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(port);

                    while (true) {
                        Socket client = serverSocket.accept();
//                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
//                        arr.add(bufferedWriter);
                            sockets.add(client);
//                new WriteThread(client, "Server");
//                //new Thread((new ClientHandle(client, bufferedWriter))).start();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        while (true) {
            for (Socket socket : sockets) {
                new Thread((new ClientHandle(socket, bufferedWriter))).start();
            }
        }


    }

    public static void sendMsg() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        for (BufferedWriter bufferedWriter : arr) {
            try {
                bufferedWriter.write(str);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void send() {

        for (Socket socket : sockets) {
            String msg ;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                while ((msg = bufferedReader.readLine()) != null) {
                    bufferedWriter.write(msg);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}