package MiscApps;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastClient {
    public static void main(String args[]) {
        System.out.println("Multicast Time Client");
        try (MulticastSocket socket = new MulticastSocket(8888)) {
            InetAddress group =
                    InetAddress.getByName("224.0.0.0");
            socket.joinGroup(group);
            System.out.println("Multicast Group Joined");

            byte[] buffer = new byte[256];
            DatagramPacket packet =
                    new DatagramPacket(buffer, buffer.length);

            for (int i = 0; i < 5; i++) {
                socket.receive(packet);
                String received = new String(packet.getData());
                System.out.println(received.trim());
            }
            socket.leaveGroup(group);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        System.out.println("Multicast Time Client Terminated");
    }
}






