package RMI;

import java.rmi.*;

public class HelloServer {
    private static final String HOST = "localhost";

    public static void main(String[] args) throws Exception {

        HelloImpl temp = new HelloImpl();
        String rmiObjectName = "rmi://" + HOST + "/Hello";
        Naming.rebind(rmiObjectName, temp);
        System.out.println("Binding complete…\n");
    }
}
