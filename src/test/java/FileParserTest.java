import com.mowitnow.mower.project.parser.FileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileParserTest {

    private FileParser parser;

    @BeforeEach
    public void setUp() {
        parser = new FileParser("src/test/resources/testFile.txt");
    }

    @DisplayName("isLawnDimensionsValidTest")
    @ParameterizedTest(name = "run #{index} : line ''{0}'' should be {1}")
    @MethodSource("parametersForIsLawnDimensionsValidTest")
    public void isLawnDimensionsValidTest(String line, boolean expectedValue) {
        boolean result = parser.isLawnDimensionsValid(line);
        assertEquals(expectedValue, result);
    }

    private static Stream<Arguments> parametersForIsLawnDimensionsValidTest() {
        return Stream.of(
                Arguments.of("", false),
                Arguments.of("55", false),
                Arguments.of("5  5", false),
                Arguments.of("A 5", false),
                Arguments.of("A A", false),
                Arguments.of("5 5", true),
                Arguments.of("6 4", true),
                Arguments.of("10 100", true)
        );
    }

    @DisplayName("isMowerPositionValidTest")
    @ParameterizedTest(name = "run #{index} : line ''{0}'' should be {1}")
    @MethodSource("parametersForIsMowerPositionValidTest")
    public void isMowerPositionValidTest(String line, boolean expectedValue) {
        boolean result = parser.isMowerPositionValid(line);
        assertEquals(expectedValue, result);
    }

    private static Stream<Arguments> parametersForIsMowerPositionValidTest() {
        return Stream.of(
                Arguments.of("", false),
                Arguments.of("3 4 WE", false),
                Arguments.of("5 5 5", false),
                Arguments.of("N E W", false),
                Arguments.of("5 3 T", false),
                Arguments.of("1 2 n", false),
                Arguments.of("1 2 N", true),
                Arguments.of("3 3 E", true),
                Arguments.of("70 10 S", true)
        );
    }

    @DisplayName("isInstructionSequenceValidTest")
    @ParameterizedTest(name = "run #{index} : line ''{0}'' should be {1}")
    @MethodSource("parametersForIsInstructionSequenceValidTest")
    public void isInstructionSequenceValidTest(String line, boolean expectedValue) {
        boolean result = parser.isInstructionSequenceValid(line);
        assertEquals(expectedValue, result);
    }

    private static Stream<Arguments> parametersForIsInstructionSequenceValidTest() {
        return Stream.of(
                Arguments.of("", false),
                Arguments.of("123123", false),
                Arguments.of("GAGAD1DA", false),
                Arguments.of("KFCKC", false),
                Arguments.of("GAGAGAA A", false),
                Arguments.of("GAGAGAGAA", true),
                Arguments.of("AADAADADDA", true),
                Arguments.of("AAA", true)
        );
    }

    @DisplayName("parseTest")
    @Test
    public void parseTest() {
        assertTrue(parser.parse());
    }
}