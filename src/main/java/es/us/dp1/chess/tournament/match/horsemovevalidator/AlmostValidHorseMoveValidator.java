package es.us.dp1.chess.tournament.match.horsemovevalidator;

public class AlmostValidHorseMoveValidator implements HorseMoveValidator {
    @Override
    public boolean isValid(int originX, int originY, int destinationX, int destinationY) {
        return Math.abs(originX - destinationX) == 2 && Math.abs(originY - destinationY) == 1
                || Math.abs(originX - destinationX) == 1 && Math.abs(originY - destinationY) == 2;
    }    
}
