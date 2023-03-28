package chess.domain.movingstrategy;

public final class MoveUpTwoRight extends KnightMovingStrategy {

    private static final MoveUpTwoRight INSTANCE = new MoveUpTwoRight(1, 2);


    private MoveUpTwoRight(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static MoveUpTwoRight instance() {
        return INSTANCE;
    }
}
