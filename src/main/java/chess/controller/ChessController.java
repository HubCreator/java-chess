package chess.controller;

import chess.dao.ChessDao;
import chess.dao.DbChessGameDao;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessGameFactory;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static chess.controller.GameCommand.END;
import static chess.controller.GameCommand.INIT;
import static chess.controller.GameCommand.MOVE;
import static chess.controller.GameCommand.SOURCE_INDEX;
import static chess.controller.GameCommand.START;
import static chess.controller.GameCommand.STATUS;
import static chess.controller.GameCommand.TARGET_INDEX;
import static chess.controller.GameCommand.from;
import static chess.controller.GameCommand.getPosition;

public final class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<GameCommand, Function<List<String>, GameCommand>> gameStatusMap;
    private final ChessDao dao;
    private ChessGame chessGame;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameStatusMap = new EnumMap<>(GameCommand.class);
        this.dao = new DbChessGameDao();
        initGameStatusMap();
    }

    private void initGameStatusMap() {
        gameStatusMap.put(START, this::start);
        gameStatusMap.put(MOVE, this::move);
        gameStatusMap.put(STATUS, this::status);
        gameStatusMap.put(END, this::end);
    }

    public void process() {
        outputView.printInitialMessage();
        GameCommand gameCommand = INIT;
        while (!gameCommand.isEnd()) {
            gameCommand = play(gameCommand);
        }
    }

    private GameCommand play(final GameCommand gameCommand) {
        try {
            final List<String> input = inputView.readCommand();
            final GameCommand newGameCommand = from(input);
            return gameStatusMap.get(newGameCommand).apply(input);
        } catch (IllegalArgumentException | IllegalStateException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return gameCommand;
        }
    }

    private GameCommand start(final List<String> input) {
        if (chessGame != null) {
            throw new IllegalArgumentException("체스 게임은 이미 진행되고 있습니다.");
        }
        if (dao.hasHistory()) {
            chessGame = dao.loadGame();
            outputView.printBoard(chessGame.getBoard());
            return MOVE;
        }
        chessGame = ChessGameFactory.generate();
        outputView.printBoard(chessGame.getBoard());
        return MOVE;
    }

    private GameCommand move(final List<String> input) {
        if (chessGame == null) {
            throw new IllegalArgumentException("체스 게임은 아직 시작하지 않았습니다.");
        }
        chessGame.move(getPosition(input, SOURCE_INDEX), getPosition(input, TARGET_INDEX));
        if (chessGame.isKingDead()) {
            dao.delete();
            outputView.printWinner(chessGame.getWinner());
            return END;
        }
        outputView.printBoard(chessGame.getBoard());
        return MOVE;
    }

    private GameCommand status(final List<String> strings) {
        dao.delete();
        outputView.printTotalScore(chessGame.calculateScore());
        outputView.printEndMessage();
        return END;
    }

    private GameCommand end(final List<String> input) {
        outputView.printEndMessage();
        dao.save(chessGame);
        return END;
    }
}
