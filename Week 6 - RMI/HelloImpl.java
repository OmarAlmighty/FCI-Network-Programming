package RMI;

import java.rmi.*;
import java.rmi.server.*;

public class HelloImpl extends UnicastRemoteObject implements Hello {
    public HelloImpl() throws RemoteException {
    }

    public String getGreeting() throws RemoteException {
        return ("Hello there!");
    }

    public int add(int x1, int x2) throws RemoteException{
        return x1 + x2;
    }
    public int subtract(int x1, int x2) throws RemoteException{
        return x1 - x2;
    }
}
