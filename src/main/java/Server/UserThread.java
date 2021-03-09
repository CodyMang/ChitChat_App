package Server;

import Server.ChatServer;

import java.io.*;
import java.net.Socket;

/**
 * This thread handles connection for each connected client, so the server
 * can handle multiple clients at the same time.
 *
 *
 */
public class UserThread extends Thread {
    private Socket socket;
    private ChatServer server;
    private PrintWriter writer;
    String userName;
    public UserThread(Socket socket, ChatServer server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

            printUsers();

            userName = reader.readLine();

            server.addUserName(userName);

            String serverMessage = "New user connected: " + userName;
            server.broadcast(serverMessage, this);


            do {

                serverMessage = reader.readLine();
                server.broadcast(serverMessage, this);

            } while (!serverMessage.equals("bye"));

            server.removeUser(userName, this);
            socket.close();

            serverMessage = userName + " has exited the chat.";
            server.broadcast(serverMessage, this);

        } catch (IOException ex) {
            System.out.println("Error in UserThread: " + ex.getMessage());
            server.broadcast(userName + " has exited the chat.",this);
            server.removeUser(userName, this);
            ex.printStackTrace();
        }
    }

    /**
     * Sends a list of online users to the newly connected user.
     */
    void printUsers() {
        if (server.hasUsers()) {
            writer.println("Connected users: " + server.getUserNames());
        } else {
            writer.println("No other users connected");
        }
    }

    /**
     * Sends a message to the client.
     */
    void sendMessage(String message) {
        writer.println(message);
    }
}
