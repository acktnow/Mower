import com.mowitnow.mower.project.model.lawn.RectangularLawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RectangularLawnTest {

    private RectangularLawn lawn;

    @BeforeEach
    public void setUp() {
        lawn = new RectangularLawn(5, 5);
    }

    @DisplayName("containsTest")
    @ParameterizedTest(name = "run #{index} : lawn contains mower at ({0},{1}) should be {2}")
    @MethodSource("parametersForContainsTest")
    public void containsTest(int x, int y, boolean expectedValue) {
        boolean result = lawn.contains(x, y);
        assertEquals(expectedValue, result);
    }

    private static Stream<Arguments> parametersForContainsTest() {
        return Stream.of(
                Arguments.of(1, 2, true),
                Arguments.of(3, 3, true),
                Arguments.of(5, 5, true),
                Arguments.of(6, 5, false),
                Arguments.of(3, 10, false)
        );
    }

    @DisplayName("isOccupiedTest")
    @ParameterizedTest(name = "run #{index} : lawn already has a mower at ({1},{2}) should be {3} " +
            "because the value in the grid is {0}")
    @MethodSource("parametersForIsOccupied")
    public void isOccupiedTest(int id, int x, int y, boolean expectedValue) {
        lawn.getLawnGrid()[x][y] = id;
        boolean result = lawn.isOccupied(x, y);
        assertEquals(expectedValue, result);
    }

    private static Stream<Arguments> parametersForIsOccupied() {
        return Stream.of(
                Arguments.of(0, 2, 3, false),
                Arguments.of(5, 4, 2, true),
                Arguments.of(6, 2, 1, true),
                Arguments.of(0, 0, 3, false)
        );
    }

    @DisplayName("placeMowerTest")
    @ParameterizedTest(name = "run #{index} : the lawnGrid at the position ({1},{2}) should contain the value {3}")
    @MethodSource("parametersForPlaceMowerTest")
    public void placeMowerTest(int id, int x, int y, int expectedValue) {
        int[][] lawnGrid = lawn.getLawnGrid();
        lawn.placeMower(id, x, y);
        assertEquals(expectedValue, lawnGrid[x][y]);
    }

    private static Stream<Arguments> parametersForPlaceMowerTest() {
        return Stream.of(
                Arguments.of(1, 1, 2, 1),
                Arguments.of(2, 3, 3, 2)
        );
    }

    @DisplayName("removeMowerTest")
    @ParameterizedTest(name = "run #{index} : the mower at the position ({1},{2}) should be removed because the lawn" +
            "grid value should be {3}")
    @MethodSource("parametersForRemoveMowerTest")
    public void removeMowerTest(int id, int x, int y, int expectedValue) {
        int[][] lawnGrid = lawn.getLawnGrid();
        lawnGrid[x][y] = id;
        lawn.removeMower(x, y);
        assertEquals(expectedValue, lawnGrid[x][y]);
    }

    private static Stream<Arguments> parametersForRemoveMowerTest() {
        return Stream.of(
                Arguments.of(1, 1, 2, 0),
                Arguments.of(3, 4, 2, 0)
        );
    }
}