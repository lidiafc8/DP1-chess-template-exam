
package es.us.dp1.chess.tournament;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;

import es.us.dp1.chess.tournament.match.ChessMatch;
import es.us.dp1.chess.tournament.match.MatchRepository;
import jakarta.persistence.EntityManager;

@DataJpaTest
public class Test6 {
    
    @Autowired
    MatchRepository dr;
        
    @Autowired
    EntityManager em;
    @Test
    public void test() {
        validatefindByComplexCriteria();
    }    

    private void 
    validatefindByComplexCriteria() {
        int d= dr.comprisedMatches(                                   
            LocalDateTime.of(2024,10,1,0,0,0),
            LocalDateTime.of(2024,12,17,0,0,0) 
        );
        assertNotNull(d);
        assertEquals(4, d);

        d= dr.comprisedMatches(                                  
            LocalDateTime.of(2024,10,12,0,0,0),
            LocalDateTime.of(2024,12,17,0,0,0)
        );
        assertNotNull(d);
        assertEquals(3, d);
        

        d= dr.comprisedMatches(                                   
            LocalDateTime.of(2024,10,14,0,0,0),
            LocalDateTime.of(2024,12,17,0,0,0)
        );
        assertNotNull(d);
        assertEquals(1, d);
        

        d= dr.comprisedMatches(                        
            LocalDateTime.of(2024,11,12,0,0,0),
            LocalDateTime.of(2024,12,17,0,0,0)

        );
        assertNotNull(d);
        assertEquals(0, d);                       

    }
        
}
