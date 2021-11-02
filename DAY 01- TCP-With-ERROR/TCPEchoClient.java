package Demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
/*
* PrintWriter out = new PrintWriter(link.getOutputStream(),true);
* */
public class TCPEchoClient {
    private static InetAddress host;
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try {
            host = InetAddress.getLocalHost();
        }catch (IOException ex){
            System.out.println(ex);
            System.exit(1);
        }
        accessServer();
    }

    private static void accessServer() {
        Socket link = null;
        try{
            link = new Socket(host, PORT);
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter out = new PrintWriter(link.getOutputStream());

            Scanner userInput = new Scanner(System.in);
            String msg, rspns;

            do {
                System.out.print("Enter Message: ");
                msg = userInput.nextLine();
                out.println(msg);
                rspns = input.nextLine();
                System.out.println("SERVER> "+ rspns);
            }while (!msg.equals("***CLOSE***"));
        }catch (IOException e){
            System.out.println(e);
            System.exit(1);
        }finally {
            try {
                link.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
