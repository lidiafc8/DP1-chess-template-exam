package es.us.dp1.chess.tournament.round;

import java.time.LocalDate;
import java.util.List;

import es.us.dp1.chess.tournament.user.User;

import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Round {
    String name;
    Integer roundNumber;
    LocalDate roundDate;
    @Transient
    Tournament tournament;
    @Transient
    List<User> participants;
}
