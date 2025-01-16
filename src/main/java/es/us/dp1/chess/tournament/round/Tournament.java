package es.us.dp1.chess.tournament.round;


import java.time.LocalDate;
import java.util.List;

import es.us.dp1.chess.tournament.user.User;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Transient;

@Getter
@Setter
public class Tournament {
    
    String name;
    
    Integer prize;
    
    LocalDate startDate;
    
    LocalDate endDate;   
    
    @Transient
    List<User> participants;
}
