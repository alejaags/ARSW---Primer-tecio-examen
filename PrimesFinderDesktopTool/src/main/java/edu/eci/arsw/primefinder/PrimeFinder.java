package edu.eci.arsw.primefinder;

import edu.eci.arsw.math.MathUtilities;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class PrimeFinder extends Thread{
    
    public static BigInteger a;
    public static BigInteger b;
    public static PrimesResultSet prs;
    
    public PrimeFinder(BigInteger a, BigInteger b, PrimesResultSet prs){
        this.a=a;
        this.b=b;
        this.prs = prs;
    }    
    public static void findPrimes(BigInteger _a, BigInteger _b, PrimesResultSet prs) {   
        a=_a;
        b=_b;
        MathUtilities mt=new MathUtilities();                
        int itCount=0;            
        BigInteger i=a;
        while (i.compareTo(b)<=0){
            itCount++;
            if (mt.isPrime(i)){
                prs.addPrime(i);
            }
            i=i.add(BigInteger.ONE);
        }        
    }
    public void run(){        
        synchronized(prs){
            findPrimes(this.a,this.b, this.prs);   
            
        }     
    }
    public void waitI() throws InterruptedException{
        prs.wait();
    }
    public void resumeT(){
        prs.notifyAll();
    }
}