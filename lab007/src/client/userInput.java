/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author Sotatek
 */
import java.io.*;

public class userInput extends Thread {

    listUser client;

    public void run() {
        BufferedReader kin = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            if (!client.logout) {
            } else {
                return;
            }
            try {
                String command = kin.readLine();
                if (command.equals("Logout")) {
                    client.send(command);
                    String response = client.read();
                    client.logout = true;
                    return;
                } else {
                    client.send(command);
                }
            } catch (Exception e) {
            }
        }
    }
}
