package chess.domain.piece;

import chess.domain.PositionFixtures;
import chess.domain.game.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RookTest {

    private final Rook rook = Rook.instance(Team.BLACK);

    @DisplayName("시작 지점과 목적 지점 사이의 모든 경로를 반환한다.")
    @Test
    void success() {
        final List<Position> path = rook.findPath(PositionFixtures.A1, PositionFixtures.A5, Team.WHITE);

        assertThat(path).containsExactly(PositionFixtures.A2, PositionFixtures.A3, PositionFixtures.A4);
    }

    @DisplayName("목적 지점이 행마법상 이동 불가능한 지역이면 예외가 발생한다.")
    @Test
    void fail_by_move_rule() {
        assertThatThrownBy(() -> rook.findPath(PositionFixtures.A1, PositionFixtures.H8, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("행마법상 이동 불가능한 지역입니다.");
    }

    @DisplayName("목적 지점에 아군의 기물이 있으면 예외가 발생한다.")
    @Test
    void fail_by_same_color_piece() {
        assertThatThrownBy(() -> rook.findPath(PositionFixtures.A1, PositionFixtures.A5, Team.BLACK))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("아군의 기물이 존재하는 곳으로는 이동할 수 없습니다.");
    }
}
