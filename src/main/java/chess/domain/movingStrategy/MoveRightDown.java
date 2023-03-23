package chess.domain.movingStrategy;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;

public final class MoveRightDown implements MovingStrategy {

    private final static MoveRightDown INSTANCE = new MoveRightDown();

    private MoveRightDown() {
    }

    public static MoveRightDown instance() {
        return INSTANCE;
    }

    @Override
    public boolean movable(final Position source, final Position target) {
        return (source.getFileOrder() < target.getFileOrder() && source.getRankOrder() > target.getRankOrder()) &&
                (target.getFileOrder() - source.getFileOrder() == source.getRankOrder() - target.getRankOrder());
    }

    @Override
    public Position move(final Position currentPosition) {
        final int fileOrder = currentPosition.getFileOrder() + 1;
        final int rankOrder = currentPosition.getRankOrder() - 1;
        return Position.of(File.of(fileOrder), Rank.of(rankOrder));
    }

    @Override
    public boolean isAttackStrategy() {
        return true;
    }
}
