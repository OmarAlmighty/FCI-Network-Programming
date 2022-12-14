package Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyStore;
import java.util.Base64;
import java.util.Scanner;

public class TCPEchoClient {
    private static InetAddress host;
    private static final int PORT = 1234;
    public static String encrypt(String plainText, SecretKey secretKey){
        try{
            //Instantiate Cipher object of type AES
            Cipher cipher = Cipher.getInstance("AES");
            //Convert the plaintext string to bytes
            byte[] plainTextBytes = plainText.getBytes();
            //Initialize the cipher with encryption mode and the secret key
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            //Encrypt the bytes of the plain text, returning the encrypted bytes
            byte[] encryptedBytes = cipher.doFinal(plainTextBytes);
            //Define a Base64 encoder object
            Base64.Encoder encoder = Base64.getEncoder();
            //Encode the encrypted bytes into string
            String encryptedText = encoder.encodeToString(encryptedBytes);
            return encryptedText;
        }catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }
    public static SecretKey getSecretKey(){
        SecretKey key = null;
        try{
            File file = new File(SecureData.KEY_STORE_FILE.value);
            KeyStore keyStore = KeyStore.getInstance(SecureData.KEY_STORE_TYPE.value);
            keyStore.load(new FileInputStream(file), SecureData.KEY_STORE_PASSWORD.value.toCharArray());
            KeyStore.PasswordProtection keyPassword = new
                    KeyStore.PasswordProtection(SecureData.KEY_PASSWORD.value.toCharArray());
            KeyStore.Entry entry = keyStore.getEntry(SecureData.KEY_ENTRY.value, keyPassword);
            key = ((KeyStore.SecretKeyEntry) entry).getSecretKey();
        }catch (Exception ex){
            System.out.println("Error");
        }
        return key;
    }
    public static void main(String[] args) {
        try {
            host = InetAddress.getLocalHost();
        } catch (IOException ioex) {
            System.out.println("Host ID not found!");
            System.exit(1);
        }
        accessServer();
    }

    private static void accessServer() {
        Socket link = null;
        SecretKey secretKey = getSecretKey();
        try{
            link = new Socket(host, PORT);
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);

            Scanner userInput = new Scanner(System.in);
            String msg, respns;

            do {
                System.out.print("Enter a message: ");
                msg = userInput.nextLine();
                String encryptedMsg = encrypt(msg, secretKey);
                output.println(encryptedMsg);
                respns = input.nextLine();
                System.out.println("\nSERVER> "+respns);
            }while (!msg.equals("***CLOSE***"));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                System.out.println("CLOSING CONNECTION ...");
                link.close();
            } catch (IOException e) {
                System.out.println("Unable to disconnect");
                System.exit(1);
            }
        }
    }
}
