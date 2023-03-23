package chess.domain.piece;

import chess.domain.movingStrategy.MoveDown;
import chess.domain.movingStrategy.MoveLeft;
import chess.domain.movingStrategy.MoveLeftDown;
import chess.domain.movingStrategy.MoveLeftUp;
import chess.domain.movingStrategy.MoveRight;
import chess.domain.movingStrategy.MoveRightDown;
import chess.domain.movingStrategy.MoveRightUp;
import chess.domain.movingStrategy.MoveUp;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;

import java.util.List;

public final class King extends NonSlidingPiece {

    private King(final Color color, final MovingStrategies strategies) {
        super(color, PieceType.KING, strategies);
    }

    public static King create(final Color color) {
        final List<MovingStrategy> rawStrategies = List.of(
                MoveRightUp.instance(), MoveRightDown.instance(), MoveLeftDown.instance(), MoveLeftUp.instance(),
                MoveUp.instance(), MoveDown.instance(), MoveLeft.instance(), MoveRight.instance());

        MovingStrategies strategies = new MovingStrategies(rawStrategies);
        return new King(color, strategies);
    }
}
