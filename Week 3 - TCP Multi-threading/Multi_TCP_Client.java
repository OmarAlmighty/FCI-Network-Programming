package Demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Multi_TCP_Client {
    private static InetAddress host;
    private static final int PORT = 1234;

    public static void main(String[] args) throws UnknownHostException {
        host = InetAddress.getLocalHost();
        try {
            sendMessages();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void sendMessages() throws IOException {
        Socket link = new Socket(host, PORT);
        Scanner input = new Scanner(link.getInputStream());
        PrintWriter output = new PrintWriter(link.getOutputStream(), true);

        Scanner userInp = new Scanner(System.in);
        String msg, rspns;
        do {
            System.out.println("Enter a message (QUIT to exit): ");
            msg = userInp.nextLine();
            output.println(msg);
            rspns = input.nextLine();
            System.out.println("SERVER> " + rspns + "\n");
        } while (!msg.equals("QUIT"));

        System.out.println("Closing connection");
        link.close();
    }
}
