import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board implements gameLogic{ // includes the board, which implements the gameLogic interface
    private int numSlots; // decreases as tiles get populated with chips
    private final int boardWidth; // width of the board
    private final int boardHeight; // height of the board
    private int moveCounter; // keeps track of the number of moves
    private final Tile[][] imageButtons; // each tile is a clickable button that can have a gray chip or a white one
    private final Icon emptySlot; //these are icons that the tiles will bear
    private final Icon whiteChip;
    private final Icon grayChip;
    private String whoseTurn; // is it white's turn or gray's turn?
    private int numWhiteChips; //number of white chips on the board
    private int numGrayChips; // number of gray chips on the board
    private JLabel whiteChipLabel; // displays how many chips white has on the window
    private JLabel grayChipLabel; // displays how many chips black has on the window
    private final JLabel whoWon; // displays who won (gray or white)
    private JLabel turnLabel; // if white or gray loses a turn, this label would indicate it as such

    public JLabel getTurnLabel() {
        return turnLabel;
    }

    public JLabel getWhoWon() { //getter method that returns the whoWon Jlabel
        return whoWon;
    }

    public void setWhoWon() { // this method sets the whoWon JLabel based on how many chips each player has
        if (numWhiteChips > numGrayChips) {
            whoWon.setText("White won!");
        }
        else if (numWhiteChips == numGrayChips) {
            whoWon.setText("Game drawn.");
        }
        else {
            whoWon.setText("Gray won!");
        }
        whoWon.setVisible(true);
    }

    public boolean gameOver() {
        return numSlots == 0;
    } // if there are no more slots remaining, the game is over

    public JLabel getWhiteChipLabel() {
        return whiteChipLabel;
    } //getter method used in Main.java

    public JLabel getGrayChipLabel() {
        return grayChipLabel;
    } //getter method used in Main.java

    public void setNumChips(Tile[][] imageButtons) {
        int whiteChips = 0;
        int grayChips = 0;
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                if (imageButtons[i][j].getCurrentIcon().equals("white")) {
                    whiteChips++;
                }
                if (imageButtons[i][j].getCurrentIcon().equals("gray")) {
                    grayChips++;
                }
            }
        }
        numWhiteChips = whiteChips;
        numGrayChips = grayChips;
    }

    public void enableValidTiles() { // enables the tiles that should be clickable
        ArrayList<Tile> currentEmptyTiles;
        currentEmptyTiles = obtainEmptyTiles(imageButtons,boardWidth,boardHeight);
        for (Tile currentEmptyTile : currentEmptyTiles) { //for each tile, do..
            // this loop checks if there are valid combinations for every empty tile

            ArrayList<Tile> upperLeftTiles = getUpperLeftTiles(currentEmptyTile, boardHeight, imageButtons, whoseTurn, false);
            if (isValidCombination(upperLeftTiles, whoseTurn)) {
                currentEmptyTile.setEnabled(true);
            }

            ArrayList<Tile> upperTiles = getUpperTiles(currentEmptyTile, boardHeight, imageButtons, whoseTurn, false);
            if (isValidCombination(upperTiles, whoseTurn)) {
                currentEmptyTile.setEnabled(true);
            }

            ArrayList<Tile> upperRightTiles = getUpperRightTiles(currentEmptyTile, boardWidth, boardHeight, imageButtons, whoseTurn, false);
            if (isValidCombination(upperRightTiles, whoseTurn)) {
                currentEmptyTile.setEnabled(true);
            }

            ArrayList<Tile> lowerLeftTiles = getLowerLeftTiles(currentEmptyTile, imageButtons, whoseTurn, false);
            if (isValidCombination(lowerLeftTiles, whoseTurn)) {
                currentEmptyTile.setEnabled(true);
            }

            ArrayList<Tile> lowerTiles = getLowerTiles(currentEmptyTile, imageButtons, whoseTurn, false);
            if (isValidCombination(lowerTiles, whoseTurn)) {
                currentEmptyTile.setEnabled(true);
            }

            ArrayList<Tile> lowerRightTiles = getLowerRightTiles(currentEmptyTile, boardWidth, imageButtons, whoseTurn, false);
            if (isValidCombination(lowerRightTiles, whoseTurn)) {
                currentEmptyTile.setEnabled(true);
            }
            ArrayList<Tile> leftTiles = getLeftTiles(currentEmptyTile, imageButtons, whoseTurn, false);

            if (isValidCombination(leftTiles, whoseTurn)) {
                currentEmptyTile.setEnabled(true);
            }

            ArrayList<Tile> rightTiles = getRightTiles(currentEmptyTile, boardWidth, imageButtons, whoseTurn, false);
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
        numSlots = (boardWidth*boardHeight)-4; // the number of tiles minus the four occupied squares in the center
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
                    //System.out.println("You pressed button " + currentButton.getxCoordinate() + "," + currentButton.getyCoordinate());
                    enableValidTiles();
                    setCombination(whoseTurn,currentButton,imageButtons);
                    disableTiles(boardWidth,boardHeight,imageButtons,whiteChip,grayChip,emptySlot);
                    moveCounter++;
                    numSlots--;
                    if (moveCounter % 2 == 1) {
                        whoseTurn = "gray";
                    }
                    if (moveCounter % 2 == 0) {
                        whoseTurn = "white";
                    }
                    turnLabel.setText(whoseTurn + "'s turn!");
                    //System.out.println("Now, it is " + whoseTurn + "'s turn!");
                    enableValidTiles();

                    if (!clickableTileExists(imageButtons)) { // A player can lose a turn if there are no valid moves
                        turnLabel.setText(whoseTurn + " cannot move (loses a turn)");
                        whoseTurn = getOppositeColor(whoseTurn);
                        enableValidTiles();
                    }


                    System.out.println("-------------------------------------");
                    setNumChips(imageButtons);
                    whiteChipLabel.setText("White chips: " + numWhiteChips);
                    grayChipLabel.setText("Gray chips: " + numGrayChips);
                    System.out.println("Num slots remaining: " + numSlots);
                    if (gameOver()) {
                        turnLabel.setVisible(false);
                        System.out.println("GAME OVER!!");
                        setWhoWon();
                    }

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
        setNumChips(imageButtons);
        turnLabel = new JLabel(whoseTurn+ "'s turn!");
        turnLabel.setForeground(Color.yellow);
        turnLabel.setBounds(240,60,400,50);
        turnLabel.setVisible(true);
        turnLabel.setFont(new Font("Arial", Font.BOLD, 15));
        whiteChipLabel = new JLabel("White chips: " + numWhiteChips);
        whiteChipLabel.setForeground(Color.yellow);
        whiteChipLabel.setBounds(10,10,200,50);
        whiteChipLabel.setFont(new Font("Arial", Font.BOLD, 20));
        grayChipLabel = new JLabel("Gray chips: " + numGrayChips);
        grayChipLabel.setForeground(Color.yellow);
        grayChipLabel.setBounds(440,10,200,50);
        grayChipLabel.setFont(new Font("Arial", Font.BOLD, 20));
        whoWon = new JLabel("");
        whoWon.setForeground(Color.yellow);
        whoWon.setFont(new Font("Arial", Font.BOLD, 20));
        whoWon.setBounds(240,10, 200,50);
        whoWon.setVisible(false);
        disableTiles(boardWidth,boardHeight,imageButtons,whiteChip,grayChip,emptySlot);
        enableValidTiles();
    }//end board constructor
}
