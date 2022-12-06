package MiscApps;

import java.net.InetAddress;

public class FindHosts {
    public static void main(String[] args) throws Exception{
        // change the netID to the current netID
        String netID = "192.168.43"; //assuming subnet mask 255.255.255
        for (int i=1;i<255;i++){
            String host=netID + "." + i;
            InetAddress address = InetAddress.getByName(host);
            if (address.isReachable(1000)){
                System.out.println(host + " is reachable");
                System.out.println(address.getCanonicalHostName());
            }
        }
    }

}
