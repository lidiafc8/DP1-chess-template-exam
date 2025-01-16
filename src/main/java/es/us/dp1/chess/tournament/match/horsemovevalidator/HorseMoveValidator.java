package es.us.dp1.chess.tournament.match.horsemovevalidator;

public interface HorseMoveValidator {
    boolean isValid(int originX, int originY, int destinationX, int destinationY);
}
