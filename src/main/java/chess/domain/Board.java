package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.UnMovablePiece;

import java.util.List;
import java.util.Map;


public final class Board {

    private static final int BOARD_LENGTH = 8;
    public static final double BOARD_SIZE = Math.pow(BOARD_LENGTH, 2);

    private final Map<Position, Piece> board;

    private Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board from(final Map<Position, Piece> board) {
        if (board.size() != Board.BOARD_SIZE) {
            throw new IllegalArgumentException(
                    String.format("체스판의 사이즈는 %d x %d 여야합니다.", BOARD_LENGTH, BOARD_LENGTH));
        }
        return new Board(board);
    }

    public void move(final Position source, final Position target) {
        final Piece sourcePiece = board.get(source);
        final Piece targetPiece = board.get(target);
        final List<Position> path = sourcePiece.findPath(source, target, targetPiece.getColor());
        if (cannotMoveToTheTarget(path)) {
            throw new IllegalArgumentException("이동하려는 경로에 기물이 존재합니다.");
        }
        board.put(target, sourcePiece);
        board.put(source, UnMovablePiece.create());
    }

    private boolean cannotMoveToTheTarget(final List<Position> path) {
        return path.stream()
                .anyMatch(position -> !board.get(position).isEmpty());
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }
}
