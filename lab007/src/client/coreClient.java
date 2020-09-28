/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Sotatek
 */
public class coreClient {

    static boolean connected;
    static boolean logout;
    static Socket cSocket;
    static PrintWriter out;
    static BufferedReader in;
    static userInput uinput;
    static readFromServer sinput;
    static DefaultListModel list;
    public static String nick;
    static String server;

    public coreClient() {
        logout = false;
        sinput = new readFromServer(this);
        uinput = new userInput();
        cSocket = null;
        out = null;
        in = null;
        boolean error;
        error = false;

        try {
            cSocket = new Socket(server, 9999);
            out = new PrintWriter(cSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
            uinput.start();
            sinput.start();
        } catch (UnknownHostException e) {
            System.out.println("Host Error" + e);

            error = true;
        } catch (IOException e) {
            System.out.println("IOException" + e);
        }
//            if (!error) {
//                nick = null;
//                nick = JOptionPane.showInputDialog(null, "NickName: ");
//                while (nick.contains(";")) {
//                    nick = JOptionPane.showInputDialog(null, "InputNickname            \";\".Cannot be null .");
//                }
//                
//            }
    }

    static void login(String nick) {
        send("Login: " + nick);
    }

    static void send(String msg) {
        out.println(msg);
    }

    static String read() {
        String s = null;
        try {
            s = in.readLine();
        } catch (Exception e) {
            System.out.println(e);
        }
        return s;
    }

}
