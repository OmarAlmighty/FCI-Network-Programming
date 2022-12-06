package Lab_3_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UDPEchoClient {
    private static InetAddress host;
    private static final int PORT = 1234;
    private static DatagramSocket datagramSocket;
    private static DatagramPacket inPacket, outPacket;
    private static byte[] buffer;

    public static void main(String[] args) {
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            System.out.println("Host ID not found!");
            System.exit(1);
        }
        accessServer();
    }

    private static void accessServer() {
        try {
            datagramSocket = new DatagramSocket(); //STEP 1
            Scanner userInput = new Scanner(System.in);
            String msg = "", rspns = "";
            do {
                System.out.print("Enter a message: ");
                msg = userInput.nextLine();

                if (!msg.equals("***CLOSE***")) {
                    outPacket = new DatagramPacket(msg.getBytes(),
                            0, msg.length(),
                            host, PORT); //STEP 2
                    datagramSocket.send(outPacket); //STEP 3
                    buffer = new byte[256]; //STEP 4
                    inPacket = new DatagramPacket
                            (buffer, buffer.length);
                    //STEP 5
                    datagramSocket.receive(inPacket); //STEP 6
                    //STEP 7
                    rspns = new String(inPacket.getData(),
                            0, inPacket.getLength());
                    System.out.println("SERVER> " + rspns);
                }
            } while (!msg.equals("***CLOSE***"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            System.out.println("\n*CLOSING CONNECTION...*");
            datagramSocket.close(); //STEP 8
            System.out.println();
        }
    }
}
