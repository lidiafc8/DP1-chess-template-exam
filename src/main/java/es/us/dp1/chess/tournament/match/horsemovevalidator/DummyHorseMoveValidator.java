package es.us.dp1.chess.tournament.match.horsemovevalidator;

public class DummyHorseMoveValidator implements HorseMoveValidator {
    public boolean isValid(int originX, int originY, int destinationX, int destinationY) {
        return true;
    }
}
