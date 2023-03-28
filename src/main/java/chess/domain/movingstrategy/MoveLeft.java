package chess.domain.movingstrategy;

import chess.domain.game.File;
import chess.domain.game.Position;

public final class MoveLeft implements MovingStrategy {

    private static final MoveLeft INSTANCE = new MoveLeft();

    private MoveLeft() {
    }

    public static MoveLeft instance() {
        return INSTANCE;
    }

    @Override
    public boolean movable(final Position source, final Position target) {
        return source.getFileOrder() > target.getFileOrder() && source.getRankOrder() == target.getRankOrder();
    }

    @Override
    public Position move(final Position currentPosition) {
        final int file = currentPosition.getFileOrder() - 1;
        return Position.of(File.of(file), currentPosition.getRank());
    }
}

