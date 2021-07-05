import org.junit.Test;

import static org.junit.Assert.*;

public class unitTests {
    //Testing the initial conditions of the game (before a move is made)
    //Tests 1 - 7 test the initial conditions of the game
    Board exampleBoard = new Board();
    @Test
    public void numSlotsTest() { //#1
        assertEquals(60, exampleBoard.getNumSlots()); // it should be 60 since four of the squares are occupied
    }
    @Test
    public void numChipsTest() { //#2
        assertEquals(2, exampleBoard.getNumWhiteChips()); // each player starts out with 2 chips on the board
        assertEquals(2,exampleBoard.getNumGrayChips());
    }
    @Test
    public void gameOverTest() { //#3
        assertFalse(exampleBoard.gameOver()); //The game cannot be over at the very beginning..
    }
    @Test
    public void widthHeightTest() { //#4
        assertEquals(8, exampleBoard.getBoardWidth()); // it's an 8 by 8 board..
        assertEquals(8, exampleBoard.getBoardHeight());
        assertEquals(8,exampleBoard.getImageButtons().length);
    }

    @Test
    public void noValidMovesTest() { //#5
        assertFalse(exampleBoard.getNoValidMoves()); // there are valid moves at the beginning..
    }
    @Test
    public void labelsTest() { //#6
        assertTrue(exampleBoard.getTurnLabel().isVisible()); // All labels should be visible except the "whoWon" label
        assertTrue(exampleBoard.getWhiteChipLabel().isVisible());
        assertTrue(exampleBoard.getGrayChipLabel().isVisible());
        assertFalse(exampleBoard.getWhoWon().isVisible());
    }

    @Test
    public void miscellaneousTest() { //# 7 - Other kinds of assertions..
        assertEquals(exampleBoard.getWhoseTurn(),"white");
        int disabledButtons = 0;
        int enabledButtons = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!exampleBoard.getImageButtons()[i][j].isEnabled()) {
                    disabledButtons++;
                }
                else {
                    enabledButtons++;
                }
            }
        }
        assertEquals(64, disabledButtons+enabledButtons); // all the buttons should sum up to 64
        // whether they're enabled or disabled
        assertEquals(4, enabledButtons); // There are four enabled buttons at first
        assertEquals(60,disabledButtons); // the rest are disabled
    }
    @Test
    public void gameplayTest1() { // #8
        while (!exampleBoard.gameOver()) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (exampleBoard.getImageButtons()[i][j].isEnabled()) {
                        exampleBoard.getImageButtons()[i][j].doClick();
                    }
                }
            }
        }
        assertEquals(0,exampleBoard.getNumSlots());
    }
}
