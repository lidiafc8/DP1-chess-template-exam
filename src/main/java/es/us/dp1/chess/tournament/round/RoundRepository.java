package es.us.dp1.chess.tournament.round;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface RoundRepository {
    Optional<Round> findById(Integer id);
    List<Round> findAll();
    //List<Round> findByTournament(Tournament tournament);
    Round save(Round round);
}
