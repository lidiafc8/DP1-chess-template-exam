package es.us.dp1.chess.tournament.match;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import es.us.dp1.chess.tournament.user.User;



public interface MatchRepository extends CrudRepository<ChessMatch,Integer>{

    @Query("SELECT -1 FROM ChessMatch m")
    Integer comprisedMatches(LocalDateTime start, LocalDateTime end);
    
    List<ChessMatch> findAll();

    List<ChessMatch> findByCreator(User creator);

    List<ChessMatch> findByOpponent(User opponent);    
    
}
