package Multithreading;

import java.io.*;
import java.net.*;
import java.util.*;

public class MultiEchoClient {
    private static InetAddress host;
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException uhEx) {
            System.out.println("\nHost ID not found!\n");
            System.exit(1);
        }
        sendMessages();
    }

    private static void sendMessages() {
        Socket socket = null;
        try {
            socket = new Socket(host, PORT);
            Scanner networkInput =
                    new Scanner(socket.getInputStream());
            PrintWriter networkOutput =
                    new PrintWriter(
                            socket.getOutputStream(), true);
            //Set up stream for keyboard entry…
            Scanner userEntry = new Scanner(System.in);
            String message, response;
            do {
                System.out.print(
                        "Enter message ('QUIT' to exit): ");
                message = userEntry.nextLine();
                //Send message to server on the
                //socket's output stream…
                //Accept response from server on the
                //socket's intput stream…
                networkOutput.println(message);
                response = networkInput.nextLine();
                //Display server's response to user…
                System.out.println(
                        "\nSERVER> " + response);
            } while (!message.equals("QUIT"));
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            try {
                System.out.println("\nClosing connection…");
                socket.close();
            } catch (IOException ioEx) {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }
}