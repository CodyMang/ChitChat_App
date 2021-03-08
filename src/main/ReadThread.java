package main;

import java.io.*;
import java.net.*;

/**
 * This thread is responsible for reading server's input and printing it
 * to the console.
 * It runs in an infinite loop until the client disconnects from the server.
 *
 * @author www.codejava.net
 */
public class ReadThread extends Thread{
    private BufferedReader reader;
    private Socket socket;
    private MainChatPage mainPage;
    //Cur
    public ReadThread(Socket socket, MainChatPage client) {
        this.socket = socket;
        this.mainPage = client;

        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                String response = reader.readLine();
                if(!response.isEmpty())
                {
                    mainPage.displayReceivedMessage(response);
                }

                // prints the username after displaying the server's message
            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }
}