package es.us.dp1.chess.tournament;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import es.us.dp1.chess.tournament.match.ChessMatch;
import es.us.dp1.chess.tournament.match.ChessMatchService;
import es.us.dp1.chess.tournament.match.ConcurrentMatchException;
import es.us.dp1.chess.tournament.match.MatchRepository;
import es.us.dp1.chess.tournament.round.RoundRepository;
import es.us.dp1.chess.tournament.user.User;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class Test8 extends ReflexiveTest{    
    MatchRepository mr;

    ChessMatchService ms;

    @Test
    public void test8CheckCreationOkWihtoutConcurrentMatches() {
        mr = mock(MatchRepository.class);
        ChessMatch m1 =createOngoingMatch();
        m1.setFinish(LocalDateTime.now());
        when(mr.findByCreator(any(User.class))).thenReturn(List.of(m1));
        when(mr.findByOpponent(any(User.class))).thenReturn(List.of());
        ms=new ChessMatchService(mr);
        ChessMatch m = createOngoingMatch();
        ms.save(m);
        verify(mr).save(m);
    }        

    @Test
    public void test8CheckCreationKOWihtConcurrentMatches() {
        mr = mock(MatchRepository.class);
        when(mr.findByCreator(any(User.class))).thenReturn(List.of(createOngoingMatch()));
        when(mr.findByOpponent(any(User.class))).thenReturn(List.of());
        ms=new ChessMatchService(mr);
        ChessMatch m = createOngoingMatch();
        assertThrows(ConcurrentMatchException.class, ()-> ms.save(m));
        verify(mr, never()).save(m);        
    }

    @Test
    public void test8CheckSaveAnnotations(){
        Class<?>[] paramTypes = {ChessMatch.class};
        checkTransactional(ChessMatchService.class, "save", ChessMatch.class);
        checkTransactionalRollback(ChessMatchService.class, "save", paramTypes, ConcurrentMatchException.class);
    }

    private ChessMatch createOngoingMatch() {
        ChessMatch m = new ChessMatch();
        m.setStart(LocalDateTime.now().minusDays(1));
        m.setCreator(Test1.createUser("Fulanito"));
        m.setOpponent(Test1.createUser("Zetanito"));
        m.setId((int)(Math.random() * 1000)+100);
        return m;
    }
}
