package chess.movingStrategy;

import chess.domain.Position;

public interface MovingStrategy {

    boolean movable(Position source, Position target);

    Position move(Position currentPosition);
}
