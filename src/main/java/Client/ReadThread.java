package Client;

import Client.MainChatPage;

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
                    int n = response.length();
                    if(n > 7 && response.contains("DELETE:"))
                    {
                        mainPage.updateActiveUsers("DELETE",response.substring(7));
                    }
                    else if (n > 4 && response.contains("ADD:"))
                    {
                        System.out.println("Trying ADD ");
                        mainPage.updateActiveUsers("ADD",response.substring(4));
                    }
                    else if (n > 6 && response.contains("USERS: "))
                    {
                        System.out.println("USERS MADE IT");
                        System.out.println(response.substring(7));
                        mainPage.updateActiveUsers("ESTABLISH",response.substring(7));
                    }
                    else
                    {
                        mainPage.displayReceivedMessage(response);
                    }
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