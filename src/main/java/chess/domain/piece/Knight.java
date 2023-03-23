package chess.domain.piece;

import chess.domain.movingStrategy.MoveDownTwoLeft;
import chess.domain.movingStrategy.MoveDownTwoRight;
import chess.domain.movingStrategy.MoveLeftTwoDown;
import chess.domain.movingStrategy.MoveLeftTwoUp;
import chess.domain.movingStrategy.MoveRightTwoDown;
import chess.domain.movingStrategy.MoveRightTwoUp;
import chess.domain.movingStrategy.MoveUpTwoLeft;
import chess.domain.movingStrategy.MoveUpTwoRight;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;

import java.util.List;

public final class Knight extends NonSlidingPiece {

    private Knight(final Color color, final MovingStrategies strategies) {
        super(color, PieceType.KNIGHT, strategies);
    }

    public static Knight create(final Color color) {
        final List<MovingStrategy> rawStrategies = List.of(
                MoveUpTwoRight.instance(), MoveUpTwoLeft.instance(), MoveRightTwoUp.instance(), MoveRightTwoDown.instance(),
                MoveDownTwoRight.instance(), MoveDownTwoLeft.instance(), MoveLeftTwoDown.instance(), MoveLeftTwoUp.instance());
        MovingStrategies strategies = new MovingStrategies(rawStrategies);

        return new Knight(color, strategies);
    }
}
