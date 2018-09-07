package edu.eci.arsw.primefinder;

import edu.eci.arsw.math.MathUtilities;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class PrimeFinder{
    
    private static List<PrimeThread> pt = new LinkedList<>();
	
    
    public static void findPrimes(BigInteger _a, BigInteger _b, PrimesResultSet prs, int numThreads) throws InterruptedException{
            
        BigInteger a=_a;
        BigInteger b=_b;

        int totalNumbers = b.intValue() - a.intValue() ;
        int div = totalNumbers/numThreads;
        System.out.println("div "+div);
        
        BigInteger tempI = a;
        BigInteger tempJ = BigInteger.valueOf(div);

        for (int i=0; i<numThreads; i++){
            if(i == (numThreads-1)){
                tempI = BigInteger.valueOf(a.intValue()+(div*i));
                tempJ = b;
                pt.add(new PrimeThread(tempI,b,prs));
            }else{
//                tempI.add(a).add(BigInteger.valueOf(div*(i+1))); 
                tempI = BigInteger.valueOf(a.intValue()+(div*(i+1))+1);
                tempJ = BigInteger.valueOf(a.intValue()+(div*(i+2)));
                pt.add(new PrimeThread(tempI,tempJ,prs));
            }

        }

        for(int i = 0; i < pt.size(); i++){
            pt.get(i).start();
        }

        for(int i = 0; i < pt.size(); i++){
            pt.get(i).join();
        }

    }
	
    public static void pause(){
        for(int i = 0; i < pt.size(); i++){
            pt.get(i).pause();
        }
    }
    
    public static void star(){
        for(int i = 0; i < pt.size(); i++){
            pt.get(i).star();
        }
    }
    
    public static boolean isRunning(){
        boolean runn = false;
        for(int i = 0; i < pt.size(); i++){
            if( pt.get(i).isRunning()){
                runn = true;
            }
        }
        return runn;
    }
	
	
	
}
