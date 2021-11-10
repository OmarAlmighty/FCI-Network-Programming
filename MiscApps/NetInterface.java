package MiscApps;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public class NetInterface {
    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> interfaceEnum =
                    NetworkInterface.getNetworkInterfaces();
            System.out.printf("Name \t Display name\n");
            for (NetworkInterface element : Collections.list(interfaceEnum)) {
                System.out.printf("%-8s %-32s\n",
                        element.getName(), element.getDisplayName());
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
    }
}
