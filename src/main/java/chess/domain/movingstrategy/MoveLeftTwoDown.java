package chess.domain.movingstrategy;

public final class MoveLeftTwoDown extends KnightMovingStrategy {

    private static final MoveLeftTwoDown INSTANCE = new MoveLeftTwoDown(-2, -1);

    private MoveLeftTwoDown(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static MoveLeftTwoDown instance() {
        return INSTANCE;
    }
}
