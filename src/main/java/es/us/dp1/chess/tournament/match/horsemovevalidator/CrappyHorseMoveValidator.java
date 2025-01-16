package es.us.dp1.chess.tournament.match.horsemovevalidator;

public class CrappyHorseMoveValidator implements HorseMoveValidator {
    @Override
    public boolean isValid(int originX, int originY, int destinationX, int destinationY) {
        return false;
    }
}
