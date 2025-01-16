package es.us.dp1.chess.tournament;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import es.us.dp1.chess.tournament.round.Round;
import es.us.dp1.chess.tournament.round.Tournament;
import jakarta.persistence.EntityManager;

@DataJpaTest()
public class Test3 extends ReflexiveTest {    
    
    @Autowired
    EntityManager em;    
    
    @Test
    public void test3InitialTournament(){                        
        Tournament m1=em.find(Tournament.class,1);
        assertNotNull(m1,"There should exist a Tournament with id:1");
        assertEquals("Los Palacios Chess Tournament",getFieldValueReflexively(m1, "name"));
        assertEquals(1000,m1.getPrize());
        assertEquals(LocalDate.of(2024, 10, 1), m1.getStartDate());
        assertEquals(LocalDate.of(2024, 10, 15),m1.getEndDate());        
    }

    @Test
    public void test3InitialRounds()
    {
        Round round1 = em.find(Round.class, 1);
        assertNotNull(round1,"Cannot find round with id "+1);
        assertEquals("SemiFinals",getFieldValueReflexively(round1,"name"));
        assertEquals(LocalDate.of(2024,10,07),round1.getRoundDate());
        assertEquals(2,round1.getRoundNumber());
        
        Round round2 = em.find(Round.class, 2);        
        assertNotNull(round2,"Cannot find round with id "+2);
        assertEquals("Finals",getFieldValueReflexively(round2,"name"));
        assertEquals(LocalDate.of(2024,10,15),round2.getRoundDate());
        assertEquals(1,round2.getRoundNumber());
        
    }       
        
    
}