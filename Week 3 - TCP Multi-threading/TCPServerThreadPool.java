package Demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TCPServerThreadPool {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        ExecutorService pool = Executors.newFixedThreadPool(3);
        System.out.println("Waiting for clients...");
        while (true){
            Socket link = serverSocket.accept();
            Thread thread = new Thread(new ClientHandler(link));
            pool.execute(thread);
        }
    }
}

class ClientHandler implements Runnable{
    Socket link = null;
    ClientHandler(Socket link){
        this.link = link;
    }
    @Override
    public void run() {
        try{
            System.out.println("Client: "+link.getRemoteSocketAddress()+" is connected");
            DataInputStream inputStream = new DataInputStream(link.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(link.getOutputStream());

            int n = inputStream.readInt();
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += i;
            }
            TimeUnit.SECONDS.sleep(10);
            outputStream.writeInt(sum);
            System.out.println("Client: "+link.getRemoteSocketAddress()+" has finished");
            System.out.println("*****************************");
        }catch (IOException ex){
            System.out.println(ex);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}