package CH02;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyLocalIPAddress {
    public static void main(String[] args) {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
            System.out.println(address);
        } catch (UnknownHostException e) {
            System.out.println("Could not find the IP address of the local host");
        }
    }
}
