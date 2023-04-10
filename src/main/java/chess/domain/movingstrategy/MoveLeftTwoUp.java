package chess.domain.movingstrategy;

public final class MoveLeftTwoUp extends KnightMovingStrategy {
    private static final MoveLeftTwoUp INSTANCE = new MoveLeftTwoUp(-2, 1);

    private MoveLeftTwoUp(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static MoveLeftTwoUp instance() {
        return INSTANCE;
    }
}
