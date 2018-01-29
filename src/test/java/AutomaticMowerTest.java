import com.mowitnow.mower.project.model.lawn.RectangularLawn;
import com.mowitnow.mower.project.model.mower.AutomaticMower;
import com.mowitnow.mower.project.model.mower.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutomaticMowerTest {

    private RectangularLawn lawn;

    private AutomaticMower mower1;

    private AutomaticMower mower2;

    @BeforeEach
    public void setup() {
        // The mowers characteristics are those given by the company
        mower1 = new AutomaticMower(1,1,2, Direction.NORTH, "GAGAGAGAA");
        mower2 = new AutomaticMower(2,3,3, Direction.EAST, "AADAADADDA");
        lawn = new RectangularLawn(5,5);
        lawn.placeMower(1, 1, 2);
        lawn.placeMower(2, 3, 3);
    }

    @DisplayName("moveMower1Test")
    @Test
    public void moveMower1Test() {
        mower1.move(lawn);
        int x1 = mower1.getPositionX();
        int y1 = mower1.getPositionY();
        Direction direction1 = mower1.getDirection();
        // Verify that the final destination after move is : 1 3 N
        assertEquals(1, x1);
        assertEquals(3, y1);
        assertEquals(Direction.NORTH, direction1);
    }

    @DisplayName("moveMower2Test")
    @Test
    public void moveMower2Test() {
        mower1.move(lawn);
        mower2.move(lawn);
        int x2 = mower2.getPositionX();
        int y2 = mower2.getPositionY();
        Direction direction2 = mower2.getDirection();
        // Verify that the final destination after move is : 5 1 E
        assertEquals(5, x2);
        assertEquals(1, y2);
        assertEquals(Direction.EAST, direction2);
    }
}
