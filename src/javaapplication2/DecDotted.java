/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication2;

/**
 *
 * @author gunte
 */
public class DecDotted {
    public DecDotted(String myIp)
    {
        ipDec = new int[4];
        
        String[] parts = myIp.split("\\.");
        if (parts.length != 4)
        {
            ArrayIndexOutOfBoundsException ex = new ArrayIndexOutOfBoundsException("X.X.X.X expected");
            throw ex;
        }
        for (int i=0; i<4; i++)
        {
            ipDec[i] = Integer.parseInt(parts[i]);
        }  
    }
    
    public DecDotted(int first, int second, int third, int fourth)
    {
        ipDec = new int[4];
        ipDec[0] = first;
        ipDec[1] = second;
        ipDec[2] = third;
        ipDec[3] = fourth;
    }
    
    public DecDotted(DecDotted ref)
    {
        ipDec = new int[4];
        for (int i=0; i<4; i++)
        {
            ipDec[i] = ref.getPart(i);
        }
    }
    
    public int getPart(int index) // 0 <= i <=3
    {
        return ipDec[index];
    }
    
    public void setPart(int index, int value)
    {
        ipDec[index] = value;
    }
    
    public void addUpPart(int index, int value)
    {
        ipDec[index] += value;
    }
    
    public String asString()
    {
        String ret;
        ret = ipDec[0] + "." + ipDec[1] + "." + ipDec[2] + "." + ipDec[3];        
        return ret;
    }
    
    public void add(int n)
    {
        int tmp = ipDec[3] + n;
        if (tmp > 255)
        {
            ipDec[3] = 255;
            ipDec[2] += tmp % 255;
        }
        else
        {
            ipDec[3] = tmp;
        }
    }
    
    public boolean less(DecDotted ref)
    {
        boolean ret = false;
        
        if (ipDec[0] < ref.getPart(0))
        {
            ret = true;
        }
        else
        {
            if (ipDec[1] < ref.getPart(1))
            {
                ret = true;
            }
            else
            {
                if (ipDec[2] < ref.getPart(2))
                {
                    ret = true;
                }
                else
                {
                    if (ipDec[3] < ref.getPart(3))
                    {
                        ret = true;
                    }
                }
            }
        }
        return ret;
    }
    private final int[] ipDec;
}
