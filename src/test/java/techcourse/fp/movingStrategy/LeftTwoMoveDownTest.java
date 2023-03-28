package techcourse.fp.movingStrategy;

import chess.domain.movingstrategy.MoveLeftTwoDown;
import chess.domain.movingstrategy.MovingStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chess.domain.PositionFixtures.A2;
import static chess.domain.PositionFixtures.B1;
import static chess.domain.PositionFixtures.B2;
import static chess.domain.PositionFixtures.B3;
import static chess.domain.PositionFixtures.C1;
import static chess.domain.PositionFixtures.C2;
import static chess.domain.PositionFixtures.C3;
import static chess.domain.PositionFixtures.D1;
import static chess.domain.PositionFixtures.D3;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LeftTwoMoveDownTest {

    private final MovingStrategy leftTwoDown = MoveLeftTwoDown.instance();

    @Nested
    public class MovableTest {

        @DisplayName("좌로 2칸, 아래로 1칸 이동하면 true를 반환한다.")
        @Test
        void success_movable() {
            assertThat(leftTwoDown.movable(C3, A2)).isTrue();
        }

        @DisplayName("상하좌우 방향은 false를 반환한다.")
        @Test
        void fail() {
            assertAll(
                    () -> assertThat(leftTwoDown.movable(C2, C3)).isFalse(),
                    () -> assertThat(leftTwoDown.movable(C2, C1)).isFalse(),
                    () -> assertThat(leftTwoDown.movable(C2, B2)).isFalse(),
                    () -> assertThat(leftTwoDown.movable(C2, A2)).isFalse()
            );
        }

        @DisplayName("대각선 방향은 false를 반환한다.")
        @Test
        void diagnol_fail() {
            assertAll(
                    () -> assertThat(leftTwoDown.movable(C2, D3)).isFalse(),
                    () -> assertThat(leftTwoDown.movable(C2, D1)).isFalse(),
                    () -> assertThat(leftTwoDown.movable(C2, B1)).isFalse(),
                    () -> assertThat(leftTwoDown.movable(C2, B3)).isFalse());
        }
    }

    @Nested
    public class MoveTest {

        @DisplayName("move시 좌로 2칸, 아래로 한 칸 움직인다.")
        @Test
        void move() {
            assertThat(leftTwoDown.move(C3)).isEqualTo(A2);
        }
    }
}
