package es.us.dp1.chess.tournament.round;

import java.util.List;

public class TournamentService {
    TournamentRepository tournamentRepository;

    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public List<Tournament> getAll() {
        return null; // TODO: Implement to solve exercise 5!
    }

    public Tournament getById(Integer id) {
        return null; // TODO: Implement to solve exercise 5!
    }

    public void save(Tournament tournament) {       
        // TODO: Implement to solve exercise 5!
    }
}
