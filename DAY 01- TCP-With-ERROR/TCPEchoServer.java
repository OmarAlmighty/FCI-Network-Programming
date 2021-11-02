package Demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
/* Solution
*PrintWriter out = new PrintWriter(link.getOutputStream(),true);
* */
public class TCPEchoServer {
    private static ServerSocket serverSocket;
    private static final int PORT = 1234;

    public static void main(String[] args) {
        System.out.println("Opening port ...");
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Port opened successfully...");
        } catch (IOException ioex) {
            System.out.println("Failed to connect to port: " + PORT);
            System.out.println("Please try another port");
            System.exit(1);
        }

        do {
            handleConnection();
        } while (true);
    }

    private static void handleConnection() {
        Socket link = null;

        try {
            link = serverSocket.accept();
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream());

            int num_msgs = 0;
            String msg = input.nextLine();

            while (!msg.equals("***CLOSE***")) {
                System.out.println("Message Received");
                num_msgs++;
                output.println("Message " + num_msgs + ": " + msg);
                msg = input.nextLine();
            }
            output.println(num_msgs + " messages received");
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } finally {
            try {
                System.out.println("\n*Closing Connection...*\n");
                link.close();
            } catch (IOException ioex) {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }
}

