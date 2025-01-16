package es.us.dp1.chess.tournament;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.us.dp1.chess.tournament.round.Round;
import es.us.dp1.chess.tournament.round.RoundRepository;
import es.us.dp1.chess.tournament.round.RoundService;
import es.us.dp1.chess.tournament.round.Tournament;
import es.us.dp1.chess.tournament.round.TournamentRepository;
import es.us.dp1.chess.tournament.round.TournamentService;

@ExtendWith(MockitoExtension.class)
public class Test5 extends ReflexiveTest{
     @Mock
    TournamentRepository tr;
    @Mock
    RoundRepository rr;

    
    TournamentService ts;    
    RoundService rs;
    
    @BeforeEach
    public void configuation(){
        ts=new TournamentService(tr);
        rs=new RoundService(rr);
    }
    
    @Test
    public void test5CheckTransactionalityOfTournamentService(){
        checkTransactional(TournamentService.class,"save", Tournament.class);        
        checkTransactional(TournamentService.class,"getAll");
    }
    
    @Test
    public void test5CheckTransactionalityOfRoundService(){
        checkTransactional(RoundService.class,"save", Round.class);        
        checkTransactional(RoundService.class,"getAll");
    }    
    
    @Test
    public void test5TournamentServiceCanGetTournaments(){
        assertNotNull(ts,"TournamentService was not injected by spring");
        when(tr.findAll()).thenReturn(List.of());
        List<Tournament> offers=ts.getAll();
        assertNotNull(offers,"The list of Tournaments found by the TournamentService was null");
        // The test fails if the service does not invoke the findAll of the repository:
        verify(tr).findAll();            
    }
    

    @Test
    public void test5RoundServiceCanGetRounds(){
        assertNotNull(rs,"RoundService was not injected by spring");
        when(rr.findAll()).thenReturn(List.of());
        List<Round> discounts=rs.getAll();
        assertNotNull(discounts,"The list of Rounds found by the RoundService was null");
        // The test fails if the service does not invoke the findAll of the repository:
        verify(rr).findAll();               
    }

    @Test
    public void test5RoundServiceCanSaveRounds(){
        assertNotNull(rs,"RoundService was not injected by spring");
        when(rr.save(any(Round.class))).thenReturn(null);    
        Round s=Test1.createValidRound(null);                
        rs.save(s);
        // The test fails if the service does not invoke the save function of the repository:
        verify(rr).save(s);
    }

    @Test
    public void test5TournamentServiceCanSaveTournaments() {
        assertNotNull(ts,"TournamentService was not injected by spring");
        when(tr.save(any(Tournament.class))).thenReturn(null);
        Tournament t=Test1.createValidTournament(null);
        ts.save(t);
        // The test fails if the service does not invoke the save function of the repository:
        verify(tr).save(t);
    }    
}