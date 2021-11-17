package Multithreading;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class MultiThreadExecutorClient {
    public static void main(String[] args) throws Exception {
        Socket c = new Socket("localhost", 1234);
        DataInputStream input = new DataInputStream(c.getInputStream());
        DataOutputStream output = new DataOutputStream(c.getOutputStream());
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter your Range: ");
        String number = userInput.readLine();

        output.writeUTF(number);

        String result = input.readUTF();
        System.out.println("The sum of 1 to " + number + " = " + result);
    }
}
