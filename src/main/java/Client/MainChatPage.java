package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

public class MainChatPage implements ActionListener {

    private final int port = 8989;
    private String hostname = "localhost";
    DataInputStream in;
    PrintWriter out;
    ServerSocket server;
    Socket socket;

    public int numOfUsersConnected = 0;
    DefaultListModel<String> users = new DefaultListModel<>();



    public MainChatPage() {

        p1.setLayout(new BorderLayout());
        users.addElement(CurrentUser.getUserName());
        activeUserList.setModel(users);

        p2.setLayout(new BorderLayout());
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(activeUserList,BorderLayout.CENTER);
        JLabel activeLabel = new JLabel("Active Users", JLabel.CENTER);
        activeLabel.setOpaque(false);
        rightPanel.add(activeLabel,BorderLayout.NORTH);
        sendButton.addActionListener(this);

        activeUserList.setBorder(javax.swing.BorderFactory.createLineBorder(Color.black));
        p1.add(sendButton, BorderLayout.EAST);
        p1.add(messageTextField, BorderLayout.CENTER);

        textDisplay.setEditable(false);
        textDisplay.setContentType("text/html");
        textDisplay.setText("<html>Establishing Connection<BR></html>");
        p2.add(textDisplay, BorderLayout.CENTER);

        mainContainer.getRootPane().setDefaultButton(sendButton);

        p2.add(p1, BorderLayout.SOUTH);
        p2.add(rightPanel, BorderLayout.EAST);

        connect();
        mainContainer.setContentPane(p2);

        mainContainer.setSize(728, 512);
        mainContainer.setVisible(true);
        mainContainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void connect()
    {
        try {
            socket = new Socket(hostname, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connected to the chat server");
            out.println(CurrentUser.getUserName());//sends username to server
            new ReadThread(socket, this).start();

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
            displayReceivedMessage("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
            displayReceivedMessage("I/O Error: " + ex.getMessage());
        }
    }
    public void displayReceivedMessage(String message)
    {
        if(!message.equals(""))
        {

            String currentText = textDisplay.getText();
            currentText = currentText.substring(0,currentText.length()-16); // removes <\body><\html>
            message = currentText + message + "<BR></body></html>";

            textDisplay.setText(message);

        }
    }

    public void displaySentMessage(String message)
    {
        {
            out.println(String.format("<font color =\"red\"><b>%s: </b></font>",CurrentUser.getUserName())+message);
            String currentText = textDisplay.getText();
            currentText = currentText.substring(0,currentText.length()-16); // removes <\body><\html>
            String myMessage = currentText + String.format("<font color =\"blue\"><b>%s: </b></font>",CurrentUser.getUserName())+message+"<BR></body></html>";
            textDisplay.setText(myMessage);
        }
        messageTextField.setText("");
    }

    private void sendButtonKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            String message = messageTextField.getText().strip();
            if(!message.equals(""))
            {
                displaySentMessage(message);
            }
        }
    }

    public void closeConnection()
    {
        try{
            socket.close();
            server.close();
            in.close();
            out.close();
        }
        catch (IOException e)
        {
            System.err.println("Error Closing Connection on port "+ port);
            e.printStackTrace();
        }
    }
    public void updateActiveUsers(String method,String newUser)
    {
        System.out.println("updating");
        switch (method) {
            case "ADD":
                System.out.println("Trying ADD");
                if (!newUser.equals(CurrentUser.getUserName())) {
                    users.addElement(newUser);
                }
                break;
            case "DELETE":
                users.removeElement(newUser);
                break;
            case "ESTABLISH":
                System.out.println("Trying Establish");
                StringTokenizer st = new StringTokenizer(newUser);
                while (st.hasMoreElements()) {

                    String tk = st.nextToken();

                    System.out.println(tk);
                    if (!users.contains(tk))
                        users.addElement(tk);
                }

                break;
        }
    }
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == sendButton)
        {
            String message = messageTextField.getText().strip();
            if(!message.equals(""))
            {
                displaySentMessage(message);
            }
        }
    }

    public static void main(String[] args)
    {
        new CurrentUser("3432423","Generic Name","me@gmail.com","todd","Dole");
        new MainChatPage();
    }


    public JFrame mainContainer = new JFrame(String.format("%s's chat",CurrentUser.getUserName()));
    public JList<String> activeUserList = new JList<>();
    public JTextField messageTextField = new JTextField();
    private JEditorPane textDisplay = new JTextPane();
    private JButton sendButton = new JButton("Send");
    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();
    private JPanel rightPanel = new JPanel();

}