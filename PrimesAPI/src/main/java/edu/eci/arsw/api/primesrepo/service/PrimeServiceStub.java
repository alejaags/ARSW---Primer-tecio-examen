package edu.eci.arsw.api.primesrepo.service;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
@Service
public class PrimeServiceStub implements PrimeService
{
    private List<FoundPrime> primes = new CopyOnWriteArrayList<>();
    
    
    
    @Override
    public void addFoundPrime( FoundPrime foundPrime ){
    boolean possible = true;
        for(FoundPrime fp: primes){
            if(fp.getPrime() == foundPrime.getPrime()){
                possible = false;
            }
        }
        if(possible){
            primes.add(foundPrime);
        }else{
            Logger.getLogger(PrimeServiceStub.class.getName()).log(Level.SEVERE, null, "No se puede agregar un numero primo que ya haya sido registrado");
        }
    }

    @Override
    public List<FoundPrime> getFoundPrimes()
    {
        //TODO
        return primes;
    }

    @Override
    public FoundPrime getPrime( String prime )    {
        FoundPrime found =  new FoundPrime();
        try{            
            for(FoundPrime fp: primes){
                if(fp.getPrime().equals(prime)){
                    found = fp;
                }

            }
        }catch(Exception e){
            Logger.getLogger(PrimeServiceStub.class.getName()).log(Level.SEVERE, null, "El numero primo a consultar no existe");
        }
        return found;
        
    }
    
}
