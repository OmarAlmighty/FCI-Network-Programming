package CH02;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UPDEchoServer {
    private static final int PORT = 1234;
    private static DatagramSocket datagramSocket;
    private static DatagramPacket inPacket, outPacket;
    private static byte[] buffer;

    public static void main(String[] args) {
        System.out.println("Opening Port...");

        try { // STEP 1
            datagramSocket = new DatagramSocket(PORT);
            System.out.println("Port opened successfully\n");
        } catch (IOException ioException) {
            System.out.println("Failed to open the port");
            System.exit(1);
        }
        handleClient();
    }

    private static void handleClient() {
        try {
            String msgIn, msgOut;
            int numMsgs = 0;
            InetAddress clientAddress = null;
            int clientPort;

            do {
                buffer = new byte[256]; //STEP 2
                inPacket = new DatagramPacket(buffer, buffer.length); //STEP 3
                datagramSocket.receive(inPacket); //STEP 4
                clientAddress = inPacket.getAddress(); //STEP 5
                clientPort = inPacket.getPort(); //STEP 5

                msgIn = new String(inPacket.getData(),
                        0, inPacket.getLength()); //STEP 6
                System.out.println("Message Received");
                numMsgs++;

                msgOut = "Message " + numMsgs + ": " + msgIn;
                outPacket = new DatagramPacket(msgOut.getBytes(),
                        msgOut.length(), clientAddress, clientPort); //STEP 7
                datagramSocket.send(outPacket); //STEP 8
            } while (true);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            System.out.println("\n*CLOSING CONNECTION...*");
            datagramSocket.close(); //STEP 9
        }
    }
}
