import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board { // includes the board and the game logic
    private static Tile pressedTile;
    private int numSlots;
    private final int boardWidth;
    private final int boardHeight;
    private int moveCounter;
    private Tile[][] imageButtons;
    private Icon emptySlot, whiteChip, grayChip;
    private String whoseTurn;

    public boolean gameOver() {
        return numSlots == 0;
    }
    public void disableTiles() {
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                if (imageButtons[i][j].getCurrentIcon().equals("white")) {
                    imageButtons[i][j].setDisabledIcon(whiteChip);
                }
                else if (imageButtons[i][j].getCurrentIcon().equals("gray")) {
                    imageButtons[i][j].setDisabledIcon(grayChip);
                }
                else {
                    imageButtons[i][j].setDisabledIcon(emptySlot);
                }
                imageButtons[i][j].setEnabled(false);
            }
        }
    }

    public void enableValidTiles() {
        //note to self: check diagonals, horizontal and vertical lines
        if (whoseTurn.equals("white")) {
            //search for a line
        }
    }

    public int getBoardWidth() {
        return this.boardWidth;
    }
    public int getBoardHeight() {
        return this.boardHeight;
    }
    public Tile[][] getImageButtons() {
        return this.imageButtons;
    }

    Board() {
        whoseTurn = "white";
        moveCounter = 0;
        boardWidth = 8;
        boardHeight = 8;
        imageButtons = new Tile[boardWidth][boardHeight];
        numSlots = boardWidth*boardHeight;
        emptySlot = new ImageIcon("src/othello_emptySlot.png");
        whiteChip = new ImageIcon("src/othello_whiteChip.png");
        grayChip = new ImageIcon("src/othello_grayChip.png");
        int startingX = 95;
        int startingY;
        int incrementFactorX = 50;
        int incrementFactorY = 50;
        for (int i = 0; i < boardWidth; i++) {
            startingY = 450;
            for (int j = 0; j < boardHeight; j++) { // middle buttons: (3,4), (4,4) = gray, (3,3) = gray, (4,3)
                Tile currentButton = new Tile();
                int finalI = i;
                int finalJ = j;
                currentButton.addActionListener(new ActionListener() { //"two or more, use a for" - E. Dijkstra
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("You pressed button " + finalI + "," + finalJ);
                        System.out.println("It was " + whoseTurn + "'s turn!");
                        moveCounter++;
                        if (moveCounter % 2 == 1) {
                            whoseTurn = "gray";
                        }
                        if (moveCounter % 2 == 0) {
                            whoseTurn = "white";
                        }
                        System.out.println("Now, it is " + whoseTurn + "'s turn!");
                    }
                });
                //currentButton.setDisabledIcon(emptySlot);
                //currentButton.setEnabled(false);
                if ((i == 3 && j == 4) || (i == 4 && j == 3)) {
                    currentButton.setWhiteChip();
                    //currentButton.setDisabledIcon(whiteChip);
                    //currentButton.setEnabled(false);
                }
                if ((i == 4 & j == 4) || (i == 3 && j ==3)) {
                    currentButton.setGrayChip();
                    //currentButton.setDisabledIcon(grayChip);
                    //currentButton.setEnabled(false);
                }
                imageButtons[i][j] = currentButton;
                imageButtons[i][j].setBounds(startingX, startingY, 50, 50);
                startingY = startingY - incrementFactorY;
            }
            startingX = startingX + incrementFactorX;
        }
        //disableTiles();
    }
}
