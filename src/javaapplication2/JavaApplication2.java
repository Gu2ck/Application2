/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication2;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;

/**
 *
 * @author gunte
 */
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello World! \n");
        
        try
        {
            String localHost = InetAddress.getLocalHost().getHostName();
            System.out.println("my hostname is " + localHost + "\n");
        }
        catch(java.net.UnknownHostException ex)
        {
            System.out.println("UnknownHostException");
        }
        
        try{        
            Enumeration e = NetworkInterface.getNetworkInterfaces();
            while(e.hasMoreElements())
            {
                NetworkInterface n = (NetworkInterface) e.nextElement();
                if (n.isUp())
                {
                    System.out.println("Dislpay name: " + n.getDisplayName());

                    List<InterfaceAddress> eee = n.getInterfaceAddresses();
                    for ( InterfaceAddress ifa : eee )
                    {
                        InetAddress a = ifa.getAddress();
                        String[] parts = a.getHostAddress().split("\\.");
                        if (parts.length == 4 && !a.isLoopbackAddress())
                        {
                            System.out.println("Device IP address: " + a.getHostAddress());
                            System.out.println("Device IP name: " + a.getHostName());
                            System.out.println("Network prefix length: " + ifa.getNetworkPrefixLength());
                            System.out.println("Broadcast address: " + ifa.getBroadcast()); 
                            System.out.println("");
                            
                            NetworkCalculator b = new NetworkCalculator(a.getHostAddress(), 23);
                            System.out.println(b.asString());
                            NetworkCalculator dd = new NetworkCalculator(a.getHostAddress(), ifa.getNetworkPrefixLength());
                            System.out.println(dd.asString());
                            NetworkCalculator d = new NetworkCalculator(a.getHostAddress(), 25);
                            System.out.println(d.asString());
                        }
                    }
                }
            }
        }
        catch(java.net.SocketException ex)
        {
            ex.printStackTrace();
        }
        // TODO code application logic here
    }
    
}
