/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication2;

/**
 *
 * @author gunte
 */
public class NetworkCalculator {
    public NetworkCalculator(String ip, int netBits)
    {
        myIp = new DecDotted(ip);
        netBitNo = netBits;
        minIP = new DecDotted(0,0,0,0);
        maxIP = new DecDotted(0,0,0,0);
        
        char[] netbits = new char[32];
        binaryNetMask = "";
        for (int i=1; i<= 32; i++ )
        {
            netbits[i-1] = '0';
            if (i <= netBits)
            {
                netbits[i-1] = '1';
            }
            if (i != 1 && ((i-1) % 8 == 0))
            {
                binaryNetMask += '.';
            }
            binaryNetMask += netbits[i-1];
        }

        netDec = new DecDotted(0,0,0,0);
        invertedNetDec = new DecDotted(0,0,0,0);
        
        for(int i=0; i<8; i++)
        {
            if (netbits[i] == '1')      {netDec.addUpPart(0, (int) Math.pow(2, 7-i));}
            if (netbits[8 + i] == '1')  {netDec.addUpPart(1, (int) Math.pow(2, 7-i));}
            if (netbits[16 + i] == '1') {netDec.addUpPart(2, (int) Math.pow(2, 7-i));}
            if (netbits[24 + i] == '1') {netDec.addUpPart(3, (int) Math.pow(2, 7-i));}

            if (netbits[i] == '0')      {invertedNetDec.addUpPart(0, (int) Math.pow(2, 7-i));}
            if (netbits[8 + i] == '0')  {invertedNetDec.addUpPart(1, (int) Math.pow(2, 7-i));}
            if (netbits[16 + i] == '0') {invertedNetDec.addUpPart(2, (int) Math.pow(2, 7-i));}
            if (netbits[24 + i] == '0') {invertedNetDec.addUpPart(3, (int) Math.pow(2, 7-i));}
        }
        for (int i=0; i<4; i++)
        {
            minIP.setPart(i, myIp.getPart(i) & netDec.getPart(i));
            maxIP.setPart(i, myIp.getPart(i) | invertedNetDec.getPart(i));
        }
        minIP.add(1);
        maxIP.add(-1);
    }
    
    public DecDotted getMyIp()
    {
        return myIp;
    }
    
    public String asString()
    {
        String ret;
        ret  = "IP: " + myIp.asString() + "/" + netBitNo;
        ret += ", BinNetMask: " + binaryNetMask;
        ret += ", NetMask: " + netDec.asString();
        ret += ", minIP: " + minIP.asString();
        ret += ", maxIP: " + maxIP.asString();
        
        return ret;
    }
    
    private final DecDotted myIp;
    private final int netBitNo;
    private String binaryNetMask;
    private final DecDotted netDec;
    private final DecDotted invertedNetDec;
    private final DecDotted minIP;
    private final DecDotted maxIP;
}
