/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication2;

import java.io.IOException;
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
                            
                            NetworkCalculator netCalc = new NetworkCalculator(a.getHostAddress(), ifa.getNetworkPrefixLength());
                            System.out.println(netCalc.infoString());
                            
                            DecDotted currIP = netCalc.getMin();
                            DecDotted bcIP = netCalc.getBroadcastIP();
                            
                            int ips = 0;
                            while (currIP.less(bcIP) && ips < 300 && currIP.getPart(0) != 10)
                            {
                                ips++;
                                System.out.println("                                                 DEBUG: " + currIP.asString());
                                //System.out.println("DEBUG: " + currIP.asString());                                    
                                try
                                {
                                    // InetAddress ip = InetAddress.getByAddress(currIP.getPart(0),currIP.getPart(1),currIP.getPart(2),currIP.getPart(3));
                                    InetAddress ia = InetAddress.getByName(currIP.asString());
                                    String deviceName = ia.getHostName();
                                    
                                    // if (ia.isReachable(n, 10, 5000))
                                    if (ia.isReachable(1000))
                                    {
                                        System.out.println(currIP.asString() + " (" + deviceName + ") reachable");
                                    }
                                    else if (!ia.getHostAddress().equals(deviceName))
                                    {
                                        System.out.println(currIP.asString() + " (" + deviceName + ") has DNS entry");
                                    }
                                    else
                                    {
                                        // System.out.println(currIP.asString() + " the host address and host name are equal, meaning the host name could not be resolved");
                                    }

                                }
                                catch(IOException ex)
                                {
                                    System.out.println(currIP.asString() + " timed out");
                                }
                                currIP.add(1);
                            }
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
