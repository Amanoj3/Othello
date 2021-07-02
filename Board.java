import javax.swing.*;
import java.util.ArrayList;

public class Board implements gameLogic{ // includes the board, which implements the gameLogic interface
    private int numSlots;
    private final int boardWidth;
    private final int boardHeight;
    private int moveCounter;
    private final Tile[][] imageButtons;
    private final Icon emptySlot;
    private final Icon whiteChip;
    private final Icon grayChip;
    private String whoseTurn;

    public boolean gameOver() {
        return numSlots == 0;
    }

    public void enableValidTiles() { // enables the tiles that should be clickable
        ArrayList<Tile> currentEmptyTiles = new ArrayList<Tile>();
        currentEmptyTiles = obtainEmptyTiles(imageButtons,boardWidth,boardHeight);
        for (Tile currentEmptyTile : currentEmptyTiles) { //for each tile, do..
            // this loop checks if there are valid combinations for every empty tile

            ArrayList<Tile> upperLeftTiles = getUpperLeftTiles(currentEmptyTile, boardHeight, imageButtons, whoseTurn);
            if (isValidCombination(upperLeftTiles, whoseTurn)) {
                currentEmptyTile.setEnabled(true);
            }

            ArrayList<Tile> upperTiles = getUpperTiles(currentEmptyTile, boardHeight, imageButtons, whoseTurn);
            if (isValidCombination(upperTiles, whoseTurn)) {
                currentEmptyTile.setEnabled(true);
            }

            ArrayList<Tile> upperRightTiles = getUpperRightTiles(currentEmptyTile, boardWidth, boardHeight, imageButtons, whoseTurn);
            if (isValidCombination(upperRightTiles, whoseTurn)) {
                currentEmptyTile.setEnabled(true);
            }

            ArrayList<Tile> lowerLeftTiles = getLowerLeftTiles(currentEmptyTile, imageButtons, whoseTurn);
            if (isValidCombination(lowerLeftTiles, whoseTurn)) {
                currentEmptyTile.setEnabled(true);
            }

            ArrayList<Tile> lowerTiles = getLowerTiles(currentEmptyTile, imageButtons, whoseTurn);
            if (isValidCombination(lowerTiles, whoseTurn)) {
                currentEmptyTile.setEnabled(true);
            }

            ArrayList<Tile> lowerRightTiles = getLowerRightTiles(currentEmptyTile, boardWidth, imageButtons, whoseTurn);
            if (isValidCombination(lowerRightTiles, whoseTurn)) {
                currentEmptyTile.setEnabled(true);
            }
            ArrayList<Tile> leftTiles = getLeftTiles(currentEmptyTile, imageButtons, whoseTurn);

            if (isValidCombination(leftTiles, whoseTurn)) {
                currentEmptyTile.setEnabled(true);
            }

            ArrayList<Tile> rightTiles = getRightTiles(currentEmptyTile, boardWidth, imageButtons, whoseTurn);
            if (isValidCombination(rightTiles, whoseTurn)) {
                currentEmptyTile.setEnabled(true);
            }
        }//end for each tile loop
    } // end enableValidTile method

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
                Tile currentButton = new Tile(i,j);
                //"two or more, use a for" - E. Dijkstra
// so each button would be able to detect clicks
                currentButton.addActionListener(e -> {
                    System.out.println("You pressed button " + currentButton.getxCoordinate() + "," + currentButton.getyCoordinate());
                    enableValidTiles();
                    disableTiles(boardWidth,boardHeight,imageButtons,whiteChip,grayChip,emptySlot);
                    moveCounter++;
                    if (moveCounter % 2 == 1) {
                        whoseTurn = "gray";
                    }
                    if (moveCounter % 2 == 0) {
                        whoseTurn = "white";
                    }
                    System.out.println("Now, it is " + whoseTurn + "'s turn!");
                    enableValidTiles();
                    System.out.println("-------------------------------------");
                });

                if ((i == 3 && j == 4) || (i == 4 && j == 3)) {
                    currentButton.setWhiteChip();
                }
                if ((i == 4 & j == 4) || (i == 3 && j ==3)) {
                    currentButton.setGrayChip();
                }

                imageButtons[i][j] = currentButton;
                imageButtons[i][j].setBounds(startingX, startingY, 50, 50);
                startingY = startingY - incrementFactorY;
            }
            startingX = startingX + incrementFactorX;
        }

        disableTiles(boardWidth,boardHeight,imageButtons,whiteChip,grayChip,emptySlot);
        enableValidTiles();
    }//end board constructor
}
