package es.us.dp1.chess.tournament;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import es.us.dp1.chess.tournament.match.ChessMatch;
import es.us.dp1.chess.tournament.round.Round;
import es.us.dp1.chess.tournament.round.Tournament;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@DataJpaTest()
public class Test2 extends ReflexiveTest{
    
    @Autowired(required = false)
    EntityManager em;         

    @Test
    public void test2TournamentAnnotations() {
        checkThatFieldIsAnnotatedWith(Tournament.class, "participants", ManyToMany.class);                                  
    }

    @Test
    public void test2RoundAnnotations() {        
        checkThatFieldIsAnnotatedWith(Round.class, "tournament", ManyToOne.class);        
        checkThatFieldIsAnnotatedWith(Round.class, "participants", ManyToMany.class);                
    }

    @Test
    public void test2ChessMatchAnnotations() {        
        checkThatFieldIsAnnotatedWith(ChessMatch.class, "round", ManyToOne.class);                
    }
    
    @Test
    private void test2TournamentConstraints() {
        Tournament t=Test1.createValidTournament(em);
        checkThatFieldsAreMandatory(t, em,"participants");        
    }

    @Test
    private void test2RoundConstraints() {
        Round r=Test1.createValidRound(em);
        checkThatFieldsAreMandatory(r, em,"participants","tournament");                
    }


}
