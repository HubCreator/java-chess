package techcourse.fp.movingStrategy;

import static org.assertj.core.api.Assertions.assertThat;
import static techcourse.fp.chess.domain.PositionFixtures.A1;
import static techcourse.fp.chess.domain.PositionFixtures.A8;
import static techcourse.fp.chess.domain.PositionFixtures.B1;
import static techcourse.fp.chess.domain.PositionFixtures.B2;
import static techcourse.fp.chess.domain.PositionFixtures.B8;
import static techcourse.fp.chess.domain.PositionFixtures.C1;
import static techcourse.fp.chess.domain.PositionFixtures.H1;
import static techcourse.fp.chess.domain.PositionFixtures.H8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import techcourse.fp.chess.movingStrategy.MovingStrategy;
import techcourse.fp.chess.movingStrategy.RightStrategy;

class RightStrategyTest {

    private final MovingStrategy rightStrategy = new RightStrategy();

    @Nested
    public class MovableTest {
        @DisplayName("Rank가 같고, target의 File이 더 크다면 true를 반환한다.")
        @Test
        void success_movable() {
            assertThat(rightStrategy.movable(A8, H8)).isTrue();
        }

        @DisplayName("Rank가 다르다면 false를 반환한다.")
        @Test
        void fail_movable() {
            assertThat(rightStrategy.movable(B2, B8)).isFalse();
        }

        @DisplayName("Rank가 같고, target의 File이 더 작다면 false를 반환한다.")
        @Test
        void fail_movable2() {
            assertThat(rightStrategy.movable(H1, A1)).isFalse();
        }
    }

    @Nested
    public class MoveTest {

        @DisplayName("move시 한 칸 오른쪽으로 움직인다.")
        @Test
        void move() {
            assertThat(rightStrategy.move(B1)).isEqualTo(C1);
        }
    }
}
