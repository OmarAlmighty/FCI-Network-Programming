package Multithreading;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MultiEchoServer {
    private static ServerSocket serverSocket;
    private static final int PORT = 1234;

    public static void main(String[] args) throws IOException{
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException ioEx) {
            System.out.println("\nUnable to set up port!");
            System.exit(1);
        }
        do {
            //Wait for client…
            Socket client = serverSocket.accept();
            System.out.println("\nNew client accepted.\n");
            //Create a thread to handle communication with
            //this client and pass the constructor for this
            //thread a reference to the relevant socket…
            ClientHandler handler =
                    new ClientHandler(client);
            handler.start();
        } while (true);
    }
}

class ClientHandler extends Thread {
    private Socket client;
    private Scanner input;
    private PrintWriter output;

    public ClientHandler(Socket socket) {
        //Set up reference to associated socket…
        client = socket;
        try {
            input = new Scanner(client.getInputStream());
            output = new PrintWriter(
                    client.getOutputStream(), true);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    public void run() {
        String received;
        do {
            //Accept message from client on
            //the socket's input stream…
            received = input.nextLine();
            //Echo message back to client on
            //the socket's output stream…
            output.println("ECHO: " + received);
            //Repeat above until 'QUIT' sent by client…
        } while (!received.equals("QUIT"));
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}