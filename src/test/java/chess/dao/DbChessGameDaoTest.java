package chess.dao;

import chess.database.DbInfo;
import chess.database.dao.ChessDao;
import chess.database.dao.DbChessGameDao;
import chess.domain.game.FakeGameFactory;
import chess.domain.game.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.dto.mapper.ParseToDomain;
import chess.dto.mapper.ParseToDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;

import static chess.domain.PositionFixtures.A1;
import static chess.domain.PositionFixtures.A2;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class DbChessGameDaoTest {

    private ChessDao dao = new DbChessGameDao(DbInfo.test_url, DbInfo.test_user, DbInfo.test_password);

    @BeforeEach
    void init() {
        dao.delete();
        dao.save(ParseToDto.parseToChessGameDto(new FakeGameFactory().generate()));
    }

    @Disabled
    @Test
    void fake_board_test() {
        final Map<Position, Piece> board = ParseToDomain.parseToBoard(dao.loadGame());

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
