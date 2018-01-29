import com.mowitnow.mower.project.model.lawn.RectangularLawn;
import com.mowitnow.mower.project.model.mower.AutomaticMower;
import com.mowitnow.mower.project.model.mower.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutomaticMowerWithCollisionTest {

    private RectangularLawn lawn;

    private AutomaticMower mower1;

    private AutomaticMower mower2;

    @BeforeEach
    public void setup() {
        // The mowers are placed in such a way that the first mower can encounter the second during the process
        // The same goes for the second mower after the first one reached his destination
        mower1 = new AutomaticMower(1,2,2, Direction.NORTH, "DADA");
        mower2 = new AutomaticMower(2,3,2, Direction.NORTH, "GGADAGA");
        lawn = new RectangularLawn(5,5);
        lawn.placeMower(1, 2, 2);
        lawn.placeMower(2, 3, 2);
    }

    @DisplayName("moveMower1Test")
    @Test
    public void moveMower1Test() {
        mower1.move(lawn);
        int x1 = mower1.getPositionX();
        int y1 = mower1.getPositionY();
        Direction direction1 = mower1.getDirection();
        // Verify that the final destination after move is : 2 1 S
        assertEquals(2, x1);
        assertEquals(1, y1);
        assertEquals(Direction.SOUTH, direction1);
    }

    @DisplayName("moveMower2Test")
    @Test
    public void moveMower2Test() {
        mower1.move(lawn);
        mower2.move(lawn);
        int x2 = mower2.getPositionX();
        int y2 = mower2.getPositionY();
        Direction direction2 = mower2.getDirection();
        // Verify that the final destination after move is : 3 0 S
        assertEquals(3, x2);
        assertEquals(0, y2);
        assertEquals(Direction.SOUTH, direction2);
    }

}
