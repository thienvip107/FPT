package components;

import java.io.*;
import java.net.*;
import java.util.*;

class server {

    static Vector clients;
    static Socket clientSocket;

    public static void main(String args[]) {
        clients = new Vector();
        clientSocket = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9999);
        } catch (IOException e) {
            System.out.println("IO " + e);
        }
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                System.out.println("new socket");
                cThread s = new cThread(clientSocket);
                clients.add(s);
                s.start();
            } catch (IOException e) {
                System.out.println("IOaccept " + e);
            }
        }
    }
}
