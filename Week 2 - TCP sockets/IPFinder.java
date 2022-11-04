package CH02;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class IPFinder {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        InetAddress address;
        System.out.print("Enter Host Name: ");

        String host = input.next();

        try{
            address = InetAddress.getByName(host);
            System.out.println("IP address: "+address);
        }catch (UnknownHostException ex){
            System.out.println("Couldn't find the host");
        }
    }
}
