package RMI;

import java.rmi.*;
public interface Hello extends Remote
{
    public String getGreeting() throws RemoteException;
    public int add(int x1, int x2) throws RemoteException;
    public int subtract(int x1, int x2) throws RemoteException;
}