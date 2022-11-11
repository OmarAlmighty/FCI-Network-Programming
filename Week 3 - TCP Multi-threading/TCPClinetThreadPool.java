package Demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TCPClinetThreadPool {
    public static void main(String[] args) throws IOException {
        Socket link = new Socket("localhost", 1234);
        DataInputStream inputStream = new DataInputStream(link.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(link.getOutputStream());
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter a range: ");
        int n = scn.nextInt();
        outputStream.writeInt(n);
        int result = inputStream.readInt();
        System.out.println("The sum of 1 to "+ n + " = " + result);
    }
}
