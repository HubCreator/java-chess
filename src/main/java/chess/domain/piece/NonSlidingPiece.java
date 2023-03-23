package chess.domain.piece;

import chess.domain.Position;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;

import java.util.Collections;
import java.util.List;

public abstract class NonSlidingPiece extends Piece {

    public NonSlidingPiece(final Color color, final PieceType pieceType, final MovingStrategies strategies) {
        super(color, pieceType, strategies);
    }

    @Override
    public List<Position> calculatePath(final MovingStrategy strategy, final Position source, final Position target, final Color targetColor) {
        final Position movedPosition = strategy.move(source);
        if (!movedPosition.equals(target)) {
            throw new IllegalArgumentException("한 칸만 이동 가능합니다.");
        }
        return Collections.emptyList();
    }
}
