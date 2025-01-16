package es.us.dp1.chess.tournament.match.horsemovevalidator;

public class StrangeMovesValidator implements HorseMoveValidator {

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

        /*
         * BUG: The correct condition for a knight move should be:
         * (deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2)
         * But we've changed it to (deltaX == 2 && deltaY == 2) || (deltaX == 1 &&
         * deltaY == 1)
         * to break the valid knight movement logic.
         */
        return (deltaX == 2 && deltaY == 2) || (deltaX == 1 && deltaY == 1);
    }

    private boolean isWithinBoardLimits(int coordinate) {
        return coordinate >= 0 && coordinate <= 7;
    }
}
