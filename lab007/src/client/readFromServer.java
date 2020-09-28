package client;

import components.*;
import client.listUser;
import java.util.*;
import javax.swing.*;

public class readFromServer extends Thread {

    listUser client;

    public readFromServer(listUser cc) {
        client = cc;
    }

    public void run() {
        String s;
        while (true) {
            if (!client.logout) {
            } else {
                return;
            }
            s = client.read();
            System.out.println("server send" + s);
            if (s.startsWith("List")) {
//                client.mainText.setText("Connected as "
//                        + client.nick);
                client.connected = true;
                client.list.clear();
                String nextNick = "";
                StringTokenizer st = new StringTokenizer(s.substring(5, s.length()), ", ");
                String temp = null;
                while (st.hasMoreTokens()) {
                    temp = st.nextToken();
//                    client.list.addElement(replace(temp, ";", ""));
                    client.updateListUser(replace(temp, ";", ""));
                }
                System.out.print("List updated: New names: ");
                for (int i = 0; i < client.list.size(); i++) {
                    System.out.print(client.list.get(i) + " ");
                }
            } else if (s.startsWith("RecieveMessageFrom")) {
                System.out.println("string cut" + s.substring(17, s.length()));
                StringTokenizer st = new StringTokenizer(s.substring(18, s.length()), "::");
                String from = st.nextToken();
                String message = "";
                    message = st.nextToken();               
                    client.addMessage(from, message);
                
            }
//            else if (s.startsWith("PrivateRecieve")) {
//                client.mainText.setText(client.mainText.getText()
//                        + "\n" + "Privat Messages: " + s.substring(14, s.length()));
//                client.mainText.setCaretPosition(client.mainText.getText().length());
//            } else if (s.startsWith("NewNick")) {
//                client.mainText.setText("");
//                String newnick = JOptionPane.showInputDialog(null,
//                        "New nick:");
//                client.connected = false;
//                client.jMenuItem1.setEnabled(true);
//                client.jMenuItem2.setEnabled(false);
//                if (newnick != null) {
//                    client.nick = newnick;
//                    client.jMenuItem1.setEnabled(false);
//                    client.jMenuItem2.setEnabled(true);
//                    client.send("Login: " + newnick);
//                }
//            }
            System.out.println(s);
//            }
        }
    }

    static String replace(String str, String pattern, String replace) {
        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer();
        while ((e = str.indexOf(pattern, s)) >= 0) {
            result.append(str.substring(s, e));
            result.append(replace);
            s = e + pattern.length();
        }
        result.append(str.substring(s));
        return result.toString();
    }
}
