package org.andreis;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    static Socket socket = null;
    static DataInputStream in;
    static DataOutputStream out;
    public static void main(String[] args) {


        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
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
            String msg = scan.nextLine();
            try {
                out.writeUTF(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
