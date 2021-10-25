package CH02;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Demo {
    private static final int PORT = 1234;
    private static InetAddress host;
    private static DatagramPacket inPacket, outPacket;
    private static DatagramSocket datagramSocket;
    private static byte[] buffer;
    public static void main(String[] args) {
        try{
            host = InetAddress.getLocalHost();
        }catch (Exception e){
            System.out.println(e);
        }

        accessServer();
    }
    private static void accessServer(){
        try {
            datagramSocket = new DatagramSocket();
            Scanner userin = new Scanner(System.in);
            String msgIn, msgOut;

            do {
                System.out.println("Enter a message: ");
                msgOut = userin.nextLine();
                outPacket = new DatagramPacket(msgOut.getBytes(), 0, msgOut.length(), host, PORT);
                datagramSocket.send(outPacket);
                buffer = new byte[256];
                inPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(inPacket);
                msgIn = new String(inPacket.getData(), 0, inPacket.getLength());
                System.out.println("SERVER> "+msgIn);

            }while (!msgIn.equals("***CLOSE***"));
        }catch (Exception e){

        }finally {

        }
    }
}
