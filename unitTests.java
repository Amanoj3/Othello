import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class unitTests {
    Board exampleBoard = new Board();
    @Test
    public void test1() {
        assertEquals(60, exampleBoard.getNumSlots());
    }
    @Test
    public void test2() {
        assertEquals(2, exampleBoard.getNumWhiteChips());
    }
    @Test
    public void test3() {
        assertEquals(2,exampleBoard.getNumGrayChips());
    }
}
