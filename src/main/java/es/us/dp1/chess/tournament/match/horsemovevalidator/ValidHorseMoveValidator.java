package es.us.dp1.chess.tournament.match.horsemovevalidator;

public class ValidHorseMoveValidator implements HorseMoveValidator {
    @Override
    public boolean isValid(int originX, int originY, int destinationX, int destinationY) {
        // Check if all coordinates are within valid board limits (0 to 7)
        if (!isWithinBoardLimits(originX) || !isWithinBoardLimits(originY)
                || !isWithinBoardLimits(destinationX) || !isWithinBoardLimits(destinationY)) {
            return false;
        }

        // Calculate absolute differences
        int deltaX = Math.abs(originX - destinationX);
        int deltaY = Math.abs(originY - destinationY);

        // Check if the move corresponds to an L shape (knight move)
        return (deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2);
    }

    private boolean isWithinBoardLimits(int coordinate) {
        return coordinate >= 0 && coordinate <= 7;
    }
}
