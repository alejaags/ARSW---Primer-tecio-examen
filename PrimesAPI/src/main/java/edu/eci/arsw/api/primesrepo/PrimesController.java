package edu.eci.arsw.api.primesrepo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.service.PrimeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
@RestController
public class PrimesController
{
    @Autowired
    PrimeService primeService;


    @RequestMapping( value = "/primes", method = RequestMethod.GET )
    public ResponseEntity<?> getPrimes(){
        ObjectMapper om = new ObjectMapper();
        try{
            return new ResponseEntity(om.writeValueAsString(primeService.getFoundPrimes()), HttpStatus.ACCEPTED);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(PrimesController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error NOT_FOUND",HttpStatus.NOT_FOUND); 
        }
    }


    //TODO implement additional methods provided by PrimeService
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> PostPrime(@RequestBody FoundPrime fp) {
        try {
            primeService.addFoundPrime(fp);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch(Exception ex) {
                Logger.getLogger(PrimesController.class.getName()).log(Level.SEVERE, null, ex);
                return new ResponseEntity<>("Error FORBIDDEN",HttpStatus.FORBIDDEN);            
        }        
    }
    
    @RequestMapping( value = "/primes/{primenumber}", method = RequestMethod.GET )
    public FoundPrime getPrime(String primenumber){
        System.out.println("Consulta un numero primo");
        return primeService.getPrime(primenumber);
    }


}
