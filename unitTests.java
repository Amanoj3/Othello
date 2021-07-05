import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
    @Test
    public void test4() {
        assertFalse(exampleBoard.gameOver());
    }
    @Test
    public void test5() {
        assertEquals(8, exampleBoard.getBoardWidth());
        assertEquals(8, exampleBoard.getBoardHeight());
    }
}
