package chess.dao;

import chess.dto.game.ChessGameLoadDto;
import chess.dto.game.ChessGameSaveDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class DbChessGameDao implements ChessDao {

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:13306/chess?useSSL=false&serverTimezone=UTC",
                    "root",
                    "root"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(final ChessGameSaveDto dto) {
        final String sql = "INSERT INTO chess_game(piece_type, piece_file, piece_rank, piece_team, last_turn) VALUES (?, ?, ?, ?, ?)";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            for (int i = 0; i < dto.getSize(); i++) {
                preparedStatement.setString(1, dto.getPieceTypes().get(i));
                preparedStatement.setString(2, dto.getPieceFiles().get(i));
                preparedStatement.setString(3, dto.getPieceRanks().get(i));
                preparedStatement.setString(4, dto.getPieceTeams().get(i));
                preparedStatement.setString(5, dto.getLastTurns().get(i));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException ignored) {
        }
    }

    @Override
    public ChessGameLoadDto loadGame() {
        final String sql = "SELECT piece_type, piece_file, piece_rank, piece_team, last_turn FROM chess_game";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql);
             final ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            final List<String> pieceType = new ArrayList<>();
            final List<String> pieceFile = new ArrayList<>();
            final List<String> pieceRanks = new ArrayList<>();
            final List<String> pieceTeams = new ArrayList<>();
            String lastTurn = "";

            while (resultSet.next()) {
                pieceType.add(resultSet.getString("piece_type"));
                pieceFile.add(resultSet.getString("piece_file"));
                pieceRanks.add(resultSet.getString("piece_rank"));
                pieceTeams.add(resultSet.getString("piece_team"));
                lastTurn = resultSet.getString("last_turn");
            }
            return new ChessGameLoadDto(pieceType, pieceFile, pieceRanks, pieceTeams, lastTurn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public boolean hasHistory() {
        final String sql = "SELECT piece_type, piece_file, piece_rank, piece_team, last_turn FROM chess_game";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql);
             final ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public void delete() {
        final String sql = "DELETE FROM chess_game";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException ignored) {
        }
    }
}
