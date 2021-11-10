package MiscApps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class URLConnect {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a url: ");
        String url = scanner.nextLine();
        accessWebsite(url);
    }

    private static void accessWebsite(String url) {
        BufferedReader br = null;
        try {
            URL my_url = new URL(url);
            URLConnection urlConnection = my_url.openConnection();

            InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream());
            br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException exception) {
            System.out.println("\n  Cannot access URL");
            System.exit(1);
        } finally {
            System.out.println("\n  Closing the connection");
            try {
                br.close();
                System.exit(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}