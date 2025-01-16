package es.us.dp1.chess.tournament.round;

import java.util.List;
import java.util.Optional;

public interface TournamentRepository {
    Optional<Tournament> findById(Integer id);
    List<Tournament> findAll();
    Tournament save(Tournament tournament);
}
