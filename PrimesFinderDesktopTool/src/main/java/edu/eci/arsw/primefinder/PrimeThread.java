/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.primefinder;

import edu.eci.arsw.math.MathUtilities;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2110111
 */
public class PrimeThread extends Thread{

    private BigInteger a;
    private BigInteger b;
    private PrimesResultSet prs;
    private volatile boolean isRunning = true;
    private volatile boolean isPaused = false;
    private final Object pau = new Object();
    
    
    public PrimeThread(BigInteger _a, BigInteger _b, PrimesResultSet prs) {
        
        a=_a;
        b=_b;
        this.prs = prs;
        
        
    }
    
    public void run(){
        MathUtilities mt=new MathUtilities();
                
        int itCount=0;

        BigInteger i=a;
        while (i.compareTo(b)<=0){
            synchronized(pau){
                if(isPaused){
                    try {
                        pau.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PrimeThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            itCount++;
            if (mt.isPrime(i)){
                prs.addPrime(i);
            }

            i=i.add(BigInteger.ONE);
        }
        isRunning = false;
    }
    
    
    public boolean pause(){
        return isPaused;
    }
    
    public void star(){
        synchronized(pau){
            isPaused = false;
            pau.notifyAll();
        }
    }
    
    public boolean isRunning(){
        return isRunning;
    }
    
    
    
//                

            
    
    
}
