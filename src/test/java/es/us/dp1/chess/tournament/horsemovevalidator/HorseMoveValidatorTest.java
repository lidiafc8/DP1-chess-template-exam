package es.us.dp1.chess.tournament.horsemovevalidator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import es.us.dp1.chess.tournament.match.horsemovevalidator.HorseMoveValidator;
import es.us.dp1.chess.tournament.match.horsemovevalidator.ValidHorseMoveValidator;


public class HorseMoveValidatorTest {
   
    // This is your SUT:
    HorseMoveValidator algorithm=new ValidHorseMoveValidator();
    
    
    

    // Please specify as many tests as you need using structures similar to this:
   
    @Test
    public void someTest(){              
        // TODO: Remove next line and write your code here
        fail();
        // Arrangement / Configuration /Fixture 
        // Act / SUT invocation  
        //Assertion:        
    }        
 

    
    public void setAlgorithm(HorseMoveValidator value){
        this.algorithm=value;
    }
        
}
