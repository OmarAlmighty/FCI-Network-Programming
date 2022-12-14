package Security;

import javax.crypto.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class TCPEchoServer {
    private static ServerSocket serverSocket;
    private static final int PORT = 1234;

    public static String decrypt(String encryptedText, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] encryptedBytes = decoder.decode(encryptedText);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            String decryptedText = new String(decryptedBytes);
            return decryptedText;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException |
                 InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public static SecretKey getSecretKey() {
        SecretKey key = null;
        try {
            File file = new File(SecureData.KEY_STORE_FILE.value);
            KeyStore keyStore = KeyStore.getInstance(SecureData.KEY_STORE_TYPE.value);
            keyStore.load(new FileInputStream(file), SecureData.KEY_STORE_PASSWORD.value.toCharArray());
            KeyStore.PasswordProtection keyPassword = new
                    KeyStore.PasswordProtection(SecureData.KEY_PASSWORD.value.toCharArray());
            KeyStore.Entry entry = keyStore.getEntry(SecureData.KEY_ENTRY.value, keyPassword);
            key = ((KeyStore.SecretKeyEntry) entry).getSecretKey();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return key;
    }

    public static void main(String[] args) {
        System.out.println("Opening port ...");
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Port opened successfully...");
        } catch (IOException ioex) {
            System.out.println("Failed to connect to port: " + PORT);
            System.out.println("Please try another port");
            System.exit(1);
        }

        do {
            handleConnection();
        } while (true);
    }

    private static void handleConnection() {
        Socket link = null;
        SecretKey secretKey = getSecretKey();
        try {
            link = serverSocket.accept();
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);

            int num_msgs = 0;
            String msgSecure = input.nextLine();
            String msg = decrypt(msgSecure, secretKey);
            while (!msg.equals("***CLOSE***")) {
                System.out.println("Message Received");
                System.out.println("Encrypted message: " + msgSecure);
                num_msgs++;
                output.println("Message " + num_msgs + ": " + msg);
                msgSecure = input.nextLine();
                msg = decrypt(msgSecure, secretKey);
            }
            output.println(num_msgs + " messages received");
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } finally {
            try {
                System.out.println("\n*Closing Connection...*\n");
                link.close();
            } catch (IOException ioex) {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }
}