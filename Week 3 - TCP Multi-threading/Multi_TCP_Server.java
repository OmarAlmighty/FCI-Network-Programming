//package Demo;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.Scanner;
//
//public class Multi_TCP_Server {
//    private static ServerSocket serverSocket;
//    private static final int PORT = 1234;
//
//    public static void main(String[] args) throws IOException {
//        serverSocket = new ServerSocket(PORT);
//        System.out.println("The server is running...");
//
//        do {
//            Socket client = serverSocket.accept();
//            System.out.println("\n*****New client connected...******");
//            ClientHandler clientHandler = new ClientHandler(client);
//            clientHandler.start();
//        } while (true);
//    }
//}
//
//class ClientHandler extends Thread {
//    private Socket client;
//    private Scanner input;
//    private PrintWriter output;
//
//    ClientHandler(Socket client) throws IOException {
//        this.client = client;
//        input = new Scanner(client.getInputStream());
//        output = new PrintWriter(client.getOutputStream(), true);
//    }
//
//    @Override
//    public void run() {
//        String recieved;
//        do {
//            recieved = input.nextLine();
//            output.println("ECHO: " + recieved);
//        } while (!recieved.equals("QUIT"));
//        if (client != null) {
//            try {
//                System.out.println("****Closing connection...****");
//                client.close();
//            } catch (IOException ex) {
//                System.out.println(ex);
//            }
//        }
//    }
//}
