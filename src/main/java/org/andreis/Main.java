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

        History.wrtEnd(new Message("Hello", "Andrei"));
        System.out.println(History.readEnd());

    }
}
