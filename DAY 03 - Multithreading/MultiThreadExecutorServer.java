package Multithreading;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadExecutorServer {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(1234);
        ExecutorService pool = Executors.newFixedThreadPool(3);
        System.out.println("waiting for clients.....");
        while (true) {
            Socket link = server.accept();
            Thread t1 = new Thread(new Client_Handler(link));
            pool.execute(t1);
        }
    }
}

class Client_Handler implements Runnable {
    Socket myClient = null;

    Client_Handler(Socket link) {
        this.myClient = link;
    }

    public void run() {
        try {
            System.out.println("Client " + myClient.getRemoteSocketAddress().toString() + " has been connected");
            DataInputStream input = new DataInputStream(myClient.getInputStream());
            DataOutputStream output = new DataOutputStream(myClient.getOutputStream());

            String NString = input.readUTF();
            int N = Integer.parseInt(NString);
            int sum = 0;
            for (int i = 1; i <= N; i++) {
                sum += i;
            }
            TimeUnit.SECONDS.sleep(10);
            output.writeUTF(String.valueOf(sum));
            System.out.println("The client " + myClient.getRemoteSocketAddress().toString() + " has finished");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
