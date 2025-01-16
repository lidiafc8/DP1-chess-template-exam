package es.us.dp1.chess.tournament;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import es.us.dp1.chess.tournament.round.Round;
import es.us.dp1.chess.tournament.round.Tournament;
import jakarta.persistence.EntityManager;

@DataJpaTest
public class Test4 extends ReflexiveTest {
   
    @Autowired
    EntityManager em;
    
    @Test
    public void test4TournamentLinks() {        
        checkContainsById(Tournament.class,1,"getParticipants",4,em);
        checkContainsById(Tournament.class,1,"getParticipants",5,em);
        checkContainsById(Tournament.class,1,"getParticipants",6,em);
        checkContainsById(Tournament.class,1,"getParticipants",7,em);        
    }

    @Test
    public void test4RoundsLinks() {        
        
        checkContainsById(Round.class,1,"getParticipants",4,em);
        checkContainsById(Round.class,1,"getParticipants",5,em);
        checkContainsById(Round.class,1,"getParticipants",6,em);
        checkContainsById(Round.class,1,"getParticipants",7,em);        

        checkContainsById(Round.class,2,"getParticipants",4,em);        
        checkContainsById(Round.class,2,"getParticipants",7,em);        
        
        
    }

    @Test
    public void test4RoundTournamentsLinks() {                         
        checkLinkedById(Round.class,1,"getTournament",1,em);
        checkLinkedById(Round.class,2,"getTournament",1,em);        
    }
    
}