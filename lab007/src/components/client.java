package components;

import components.readFromServer;
import client.userInput;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class client extends JFrame {

    static boolean connected;
    static boolean logout;
    static Socket cSocket;
    static PrintWriter out;
    static BufferedReader in;
    static userInput uinput;
    static readFromServer sinput;
    static DefaultListModel list;

    void run() {
        setTitle("Simple Java Chat");
        enter = false;
        connected = false;
        jScrollPane1 = new javax.swing.JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        inputText = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainText = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        list = new DefaultListModel();
        list.addElement("Not Connected");
        nickList = new JList(list);
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        inputText.setColumns(20);
        inputText.setRows(5);
        jScrollPane1.setViewportView(inputText);
        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });
        inputText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputTextKeyReleased(evt);
            }
        });
        nickList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nickListMouseClicked(evt);
            }
        });
        inputText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputTextKeyReleased(evt);
            }
        });
        mainText.setColumns(20);
        mainText.setEditable(false);
        mainText.setRows(5);
        mainText.setLineWrap(true);
        inputText.setLineWrap(true);
        jScrollPane2.setViewportView(mainText);
        jScrollPane3.setViewportView(nickList);
//menu
        jMenu1.setText("Login");
        jMenu2.setText("Help");
        jMenuItem1.setText("Connect");
        jMenuItem2.setText("Disconnect");
        jMenuItem3.setText("Usage");
        jMenuItem2.setEnabled(false);
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed3(evt);
            }
        });
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed2(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jMenuItem2);
        jMenu2.add(jMenuItem3);
        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);
        setJMenuBar(jMenuBar1);
        make();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        mainText.setFont(new Font("Serif", Font.ITALIC, 16));
    }

    void make() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING
                                        )
                                                .addComponent(jScrollPane2,
                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                                                .addComponent(jScrollPane1,
                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                                        )
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                false)
                                                .addComponent(jScrollPane3)
                                                .addComponent(sendButton,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                        )
                                        .addContainerGap()
                        )
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING
                                        )
                                                .addComponent(jScrollPane3,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                                                .addComponent(jScrollPane2,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                                        )
                                        .addGap(9, 9, 9)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                false)
                                                .addComponent(sendButton,
                                                        javax.swing.GroupLayout.Alignment.TRAILING,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                                        )
                                        .addContainerGap()
                        )
        );
        pack();
        setVisible(true);
    }

    private void nickListMouseClicked(java.awt.event.MouseEvent evt) {
        if (connected && (!nickList.getSelectedValue().equals(nick))) {
            String msg = JOptionPane.showInputDialog(null, "server:");
            if (msg != null) {
                send("PrivateMessageFrom " + msg
                        + "," + nickList.getSelectedValue());
            }
            System.out.println(nickList.getSelectedValue());
        }
    }
    static boolean enter;

    private void inputTextKeyReleased(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == 10) {
            if (enter) {
                sendInput();
                enter = false;
            } else {
                enter = true;
            }
        }
    }

    private void jMenuItem1ActionPerformed2(java.awt.event.ActionEvent evt) {
        send("Logout");
        client.logout = true;
        System.exit(0);
    }

    private void jMenuItem1ActionPerformed3(java.awt.event.ActionEvent evt) {
        String s;
        s = "Login and Connect. Connect to localhost then input yourusername\n       ";
        JOptionPane.showMessageDialog(this, s, "Usage",
                JOptionPane.INFORMATION_MESSAGE);
    }
    static String server;

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
        setTitle("Connecting ...");
        logout = false;
        sinput = new readFromServer(this);
        uinput = new userInput();
        cSocket = null;
        out = null;
        in = null;
        boolean error;
        error = false;
        server = JOptionPane.showInputDialog("Server: ", "localhost");
        try {
            cSocket = new Socket(server, 9999);
            out = new PrintWriter(cSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
            uinput.start();
            sinput.start();
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(this, "No server isound", "ERROR           ", JOptionPane.ERROR_MESSAGE);
            System.out.println("Host Error" + e);
            setTitle(
                    "Simple Java Chat - Cannot Connect Please Tryanother server           ");
            error = true;
        } catch (IOException e) {
            System.out.println("IOException" + e);
        }
        if (!error) {
            nick = null;
            nick = JOptionPane.showInputDialog(null, "NickName: ");
            while (nick.contains(";")) {
                nick = JOptionPane.showInputDialog(null, "InputNickname            \";\".Cannot be null .");
            }
            send("Login: " + nick);
            if (nick != null) {
                jMenuItem1.setEnabled(false);
                jMenuItem2.setEnabled(true);
            }
        }
    }

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {
        sendInput();
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

    void sendInput() {
        if (!connected) {
            JOptionPane.showMessageDialog(this, "Not connected! Actions -Connect", "E", JOptionPane.ERROR_MESSAGE);
            inputText.setText("");
        } else if (inputText.getText().equals("")
                || inputText.getText().equals("\n") || inputText.getText() == null) {
            inputText.setText("");
        } else {
            send("Post " + replace(inputText.getText(), "\n", " "));
            inputText.setText("");
        }
    }
    public static String nick;
    private javax.swing.JTextArea inputText;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    static public javax.swing.JMenuItem jMenuItem1;
    static public javax.swing.JMenuItem jMenuItem2;
    static public javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTextArea mainText;
    private javax.swing.JList nickList;
    private javax.swing.JButton sendButton;
}
