package org.andreis;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    static Socket socket = null;
    private static final Logger LOGGER = Logger.getLogger("Server");
    static DataInputStream in;
    static DataOutputStream out;
    public static void main(String[] args) {


        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            LOGGER.info("Сервер запущен, ожидает подключения.");
            socket = serverSocket.accept();
            LOGGER.info("Клиент подключился");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String str = in.readUTF();
                System.out.println(str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            Scanner scan = new Scanner(System.in);
            while (true) {

                String msg = scan.nextLine();
                System.out.println("SENDED MESSAGE");
                try {
                    out.writeUTF(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
