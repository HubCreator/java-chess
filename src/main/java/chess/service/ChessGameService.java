package chess.service;

import chess.dao.ChessDao;
import chess.domain.game.ChessGame;
import chess.domain.gamefactory.DefaultGameFactory;
import chess.dto.game.ChessGameLoadDto;
import chess.utils.ParseToDomain;
import chess.utils.ParseToDto;

public final class ChessGameService {

    private final ChessDao dao;

    public ChessGameService(final ChessDao dao) {
        this.dao = dao;
    }

    public void save(final ChessGame chessGame) {
        dao.delete();
        dao.save(ParseToDto.parseToChessGameDto(chessGame));
    }

    public ChessGameLoadDto loadGame() {
        return dao.loadGame();
    }

    public void delete() {
        dao.delete();
    }

    public boolean hasHistory() {
        return dao.hasHistory();
    }

    public ChessGame load() {
        if (hasHistory()) {
            final ChessGameLoadDto chessGameLoadDto = loadGame();
            return ChessGame.of(
                    ParseToDomain.parseToBoard(chessGameLoadDto),
                    ParseToDomain.parseToTurn(chessGameLoadDto));
        }
        return new DefaultGameFactory().generate();
    }
}
