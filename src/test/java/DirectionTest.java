import com.mowitnow.mower.project.model.mower.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @DisplayName("turnLeftTest")
    @ParameterizedTest(name = "run #{index} : turn left from {0} should be {1}")
    @MethodSource("parametersForTurnLeftTest")
    public void turnLeftTest(Direction direction, Direction expectedValue) {
        assertEquals(expectedValue, direction.turnLeft());
    }

    private static Stream<Arguments> parametersForTurnLeftTest() {
        return Stream.of(
                Arguments.of(Direction.NORTH, Direction.WEST),
                Arguments.of(Direction.EAST, Direction.NORTH),
                Arguments.of(Direction.WEST, Direction.SOUTH),
                Arguments.of(Direction.SOUTH, Direction.EAST)
        );
    }

    @DisplayName("turnRightTest")
    @ParameterizedTest(name = "run #{index} : turn right from {0} should be {1}")
    @MethodSource("parametersForTurnRightTest")
    public void turnRightTest(Direction direction, Direction expectedValue) {
        assertEquals(expectedValue, direction.turnRight());
    }

    private static Stream<Arguments> parametersForTurnRightTest() {
        return Stream.of(
                Arguments.of(Direction.NORTH, Direction.EAST),
                Arguments.of(Direction.EAST, Direction.SOUTH),
                Arguments.of(Direction.WEST, Direction.NORTH),
                Arguments.of(Direction.SOUTH, Direction.WEST)
        );
    }

    @DisplayName("fromStringTest")
    @ParameterizedTest(name = "run #{index} : string {0} corresponds to enum {1}")
    @MethodSource("parametersForFromStringTest")
    public void fromStringTest(String symbol, Direction expectedValue) {
        assertEquals(expectedValue, Direction.fromString(symbol));
    }

    private static Stream<Arguments> parametersForFromStringTest() {
        return Stream.of(
                Arguments.of("N", Direction.NORTH),
                Arguments.of("E", Direction.EAST),
                Arguments.of("W", Direction.WEST),
                Arguments.of("S", Direction.SOUTH)
        );
    }
}
