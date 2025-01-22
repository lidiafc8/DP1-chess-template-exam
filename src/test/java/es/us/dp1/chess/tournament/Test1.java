package es.us.dp1.chess.tournament;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.stereotype.Service;

import es.us.dp1.chess.tournament.match.ChessMatch;
import es.us.dp1.chess.tournament.round.Round;
import es.us.dp1.chess.tournament.round.RoundRepository;
import es.us.dp1.chess.tournament.round.Tournament;
import es.us.dp1.chess.tournament.round.TournamentRepository;
import es.us.dp1.chess.tournament.user.Authorities;
import es.us.dp1.chess.tournament.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintViolation;

@DataJpaTest()
//@Disabled
public class Test1 extends ReflexiveTest{

    @Autowired(required = false)
    TournamentRepository tournamentRepo;
    @Autowired(required = false)
    RoundRepository roundRepo;
    
    @Autowired
    EntityManager em;
 
    @Test
    public void test1RepositoriesExist(){
        assertNotNull(tournamentRepo,"The tournament repository was not injected into the tests, its autowired value was null");
        assertNotNull(roundRepo,"The round repository was not injected into the tests, its autowired value was null");
        test1RepositoriesContainsMethod();
    }

    public void test1RepositoriesContainsMethod(){
        if(tournamentRepo!=null){
            Object v=tournamentRepo.findById(12);
            assertFalse(null!=v && ((Optional)v).isPresent(), "No result (null) should be returned for a tournament that does not exist");
        }else
            fail("The tournament repository was not injected into the tests, its autowired value was null");
        
        if(roundRepo!=null){
            Object v=roundRepo.findById(12);
            assertFalse(null!=v && ((Optional)v).isPresent(), "No result (null) should be returned for a round that does not exist");
        }else
            fail("The round repository was not injected into the tests, its autowired value was null");
    }
    
    

    
    @Test
    public void test1CheckRoundConstraints() {
        Map<String,List<Object>> invalidValues=Map.of( 
                                        "name",     List.of(
                                                        "      ","a",
                                                        "aaaa",
                                                        "En un lugar de la Mancha, de cuyo nombre no quiero acordarme, no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y galgo corredor. Una olla de algo más vaca que carnero, salpicón las más noches, duelos y quebrantos los sábados, lentejas los viernes, algún palomino de añadidura los domingos, consumían las tres partes de su hacienda. "),                                           
                                            "roundNumber",  List.of(-1, 0, 1001)                                           
                                            );


        Round r=createValidRound(em);
        if(r.getTournament()!=null)
            em.persist(r.getTournament());
        em.persist(r);
        
        checkThatFieldsAreMandatory(r, em, "roundDate");        
        
        checkThatValuesAreNotValid(r, invalidValues,em);   
    }

     @Test
    public void test1CheckTournamentContraints() {
         Map<String,List<Object>> invalidValues=Map.of(
                                            "name",     List.of(
                                                    "      ",
                                                    "a",
                                                    "aaaa",
                                                    "En un lugar de la Mancha, de cuyo nombre no quiero acordarme, no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y galgo corredor. Una olla de algo más vaca que carnero, salpicón las más noches, duelos y quebrantos los sábados, lentejas los viernes, algún palomino de añadidura los domingos, consumían las tres partes de su hacienda. "),
                                            "prize", List.of(-1,0)
                                            );

                                        
        Tournament t=createValidTournament(em);
        em.persist(t);
        
        checkThatFieldsAreMandatory(t, em, "name","startDate","endDate","prize");        
        
        checkThatValuesAreNotValid(t, invalidValues,em);   
    }

    
    @Test
    public void test1CheckTournamentAnnotations() {        
        assertTrue(classIsAnnotatedWith(Tournament.class,Entity.class));
    }

    @Test
    public void test1CheckRoundAnnotations() {
        assertTrue(classIsAnnotatedWith(Round.class,Entity.class));
    }

    public static Tournament createValidTournament(EntityManager em){        
        Tournament tournament=new Tournament();        
        setValue(tournament,"name",String.class,"Torneo de Ajedrez de Los Palacios");
        User u1=null;
        User u2=null;
        if(em!=null){
            u1=em.find(User.class, 4);
            u2=em.find(User.class, 5);        
        }else{
            u1=createUser("Pepe");
            u2=createUser("Juan");
        }
        tournament.setStartDate(LocalDate.of(2024, 12, 1)); 
        tournament.setEndDate(LocalDate.of(2024, 12, 20));
        tournament.setPrize(1000);
        tournament.setParticipants(List.of(u1,u2));        
        return tournament;
    }

    public static Round createValidRound(EntityManager em){
        
        User u1=null;
        User u2=null;
        if(em!=null){
            u1=em.find(User.class, 4);
            u2=em.find(User.class, 5);
        }else{
            u1=createUser("Pepe");
            u2=createUser("Juan");
        }
        Round round = new Round();
        setValue(round, "name", String.class, "Finals");        
        round.setRoundNumber(1);  // Valor dentro del rango
        round.setRoundDate(LocalDate.of(2024, 12, 1));        
        round.setParticipants(List.of(u1,u2));
        round.setTournament(createValidTournament(em));
        return round;
    }

    public static User createUser(String name){
        Authorities a1=new Authorities();
        a1.setAuthority("PANA");
        User u1=new User();
        setValue(u1,"username",String.class,name);
        setValue(u1, "authority", Authorities.class, a1);
        return u1;
    }
        
}
