package MiscApps;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class MulticastServer {
    public static void main(String[] args) {
        System.out.println("Multicast Time Server");
        DatagramSocket serverSocket = null;

        try {
            serverSocket = new DatagramSocket();
            while (true) {
                String dateText = new Date().toString();
                byte[] buffer = new byte[256];
                buffer = dateText.getBytes();

                InetAddress group = InetAddress.getByName("224.0.0.0");
                DatagramPacket packet;
                packet = new DatagramPacket(buffer, buffer.length,
                        group, 8888);
                serverSocket.send(packet);
                System.out.println("Time sent: " + dateText);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                    System.exit(1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}



