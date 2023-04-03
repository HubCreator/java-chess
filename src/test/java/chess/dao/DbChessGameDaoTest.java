package chess.dao;

import chess.domain.game.ChessGame;
import chess.domain.game.FakeGameFactory;
import chess.domain.game.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;

import static chess.domain.PositionFixtures.A1;
import static chess.domain.PositionFixtures.A2;
import static org.assertj.core.api.Assertions.assertThat;

class DbChessGameDaoTest {

    private ChessGame chessGame;

    @BeforeEach
    void init() {
        chessGame = new FakeGameFactory().generate();
    }

    @Disabled
    @Test
    void fake_board_test() {
        final Map<Position, Piece> board = chessGame.getBoard();
        final Map<Position, Piece> result = board.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSamePieceTypeAs(PieceType.ROOK) ||
                        entry.getValue().isSamePieceTypeAs(PieceType.BISHOP))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        assertThat(result).containsOnly(
                Map.entry(A2, Bishop.instance(Team.WHITE)),
                Map.entry(A1, Rook.instance(Team.WHITE)));
    }
}
