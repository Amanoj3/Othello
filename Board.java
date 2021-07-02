import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board implements gameLogic{ // includes the board, which implements the gameLogic interface
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

    public void enableValidTiles(Tile clickedTile) {
        ArrayList<Tile> currentEmptyTiles = new ArrayList<Tile>();
        if (clickedTile == null) {
            currentEmptyTiles = obtainEmptyTiles(imageButtons,boardWidth,boardHeight);
        }
        else {
            currentEmptyTiles.add(clickedTile);
        }

            for (int i = 0; i < currentEmptyTiles.size(); i++) { //for each tile, do..
                // this loop checks if there are valid combinations for every empty tile
                ArrayList<Tile> upperLeftTiles = new ArrayList<Tile>();

                //check upper left diagonal
                int currentX = currentEmptyTiles.get(i).getxCoordinate()-1;
                int currentY = currentEmptyTiles.get(i).getyCoordinate()+1;
                while (currentX >=0 && currentY < boardHeight) {
                    if (emptyOrWhoseTurn(imageButtons[currentX][currentY],whoseTurn)) {
                        upperLeftTiles.add(imageButtons[currentX][currentY]);
                        break;
                    }
                    else {
                        upperLeftTiles.add(imageButtons[currentX][currentY]);
                    }
                    currentX--;
                    currentY++;
                }
                if (clickedTile == null) {
                    if (isValidCombination(upperLeftTiles,whoseTurn)) {
                        currentEmptyTiles.get(i).setEnabled(true);
                    }// end upper left check
                }
                else {
                    System.out.println("UpperLeftTiles size: " + upperLeftTiles.size());
                    setCombination(whoseTurn,upperLeftTiles,currentEmptyTiles.get(i));
                }

                //check upper
                currentX = currentEmptyTiles.get(i).getxCoordinate();
                currentY = currentEmptyTiles.get(i).getyCoordinate()+1;
                ArrayList<Tile> upperTiles = new ArrayList<>();
                while (currentY < boardHeight) {
                    if (emptyOrWhoseTurn(imageButtons[currentX][currentY],whoseTurn)) {
                        upperTiles.add(imageButtons[currentX][currentY]);
                        break;
                    }
                    else {
                        upperTiles.add(imageButtons[currentX][currentY]);
                    }
                    currentY++;
                }
                if (clickedTile == null) {
                    if (isValidCombination(upperTiles, whoseTurn)) {
                        currentEmptyTiles.get(i).setEnabled(true);
                    }//end upper check
                }
                else {
                    System.out.println("UpperTiles size: " + upperTiles.size());
                    setCombination(whoseTurn,upperTiles,currentEmptyTiles.get(i));
                }
                //upper right check
                currentX = currentEmptyTiles.get(i).getxCoordinate()+1;
                currentY = currentEmptyTiles.get(i).getyCoordinate()+1;
                ArrayList<Tile> upperRightTiles = new ArrayList<Tile>();
                while (currentX < boardWidth && currentY < boardHeight) {
                    if (emptyOrWhoseTurn(imageButtons[currentX][currentY],whoseTurn)) {
                        upperRightTiles.add(imageButtons[currentX][currentY]);
                        break;
                    }
                    else {
                        upperRightTiles.add(imageButtons[currentX][currentY]);
                    }
                    currentX++;
                    currentY++;
                }
                if (clickedTile == null) {
                    if (isValidCombination(upperRightTiles, whoseTurn)) {
                        currentEmptyTiles.get(i).setEnabled(true);
                    }//end upper right check
                }
                else {
                    setCombination(whoseTurn,upperRightTiles,currentEmptyTiles.get(i));
                }
                //lower left check
                currentX = currentEmptyTiles.get(i).getxCoordinate()-1;
                currentY = currentEmptyTiles.get(i).getyCoordinate()-1;
                ArrayList<Tile> lowerLeftTiles = new ArrayList<Tile>();
                while (currentX >= 0 && currentY >=0) {
                    if (emptyOrWhoseTurn(imageButtons[currentX][currentY],whoseTurn)) {
                        lowerLeftTiles.add(imageButtons[currentX][currentY]);
                        break;
                    }
                    else {
                        lowerLeftTiles.add(imageButtons[currentX][currentY]);
                    }
                    currentX--;
                    currentY--;
                }
                if (clickedTile == null) {
                    if (isValidCombination(lowerLeftTiles, whoseTurn)) {
                        currentEmptyTiles.get(i).setEnabled(true);
                    }// end lower left check
                }
                else {
                    setCombination(whoseTurn,lowerLeftTiles,currentEmptyTiles.get(i));
                }
                //lower check
                currentX = currentEmptyTiles.get(i).getxCoordinate();
                currentY = currentEmptyTiles.get(i).getyCoordinate()-1;
                ArrayList<Tile> lowerTiles = new ArrayList<Tile>();
                while (currentY >= 0) {
                    if (emptyOrWhoseTurn(imageButtons[currentX][currentY],whoseTurn)) {
                        lowerTiles.add(imageButtons[currentX][currentY]);
                        break;
                    }
                    else {
                        lowerTiles.add(imageButtons[currentX][currentX]);
                    }
                    currentY--;
                }
                if (clickedTile == null) {
                    if (isValidCombination(lowerTiles, whoseTurn)) {
                        currentEmptyTiles.get(i).setEnabled(true);
                    }// end lower check
                }
                else {
                    setCombination(whoseTurn,lowerTiles,currentEmptyTiles.get(i));
                }
                //lower right check
                currentX = currentEmptyTiles.get(i).getxCoordinate()+1;
                currentY = currentEmptyTiles.get(i).getyCoordinate()-1;
                ArrayList<Tile> lowerRightTiles = new ArrayList<Tile>();
                while (currentX < boardWidth && currentY >=0) {
                    if (emptyOrWhoseTurn(imageButtons[currentX][currentY],whoseTurn)) {
                        lowerRightTiles.add(imageButtons[currentX][currentY]);
                        break;
                    }
                    else {
                        lowerRightTiles.add(imageButtons[currentX][currentY]);
                    }
                    currentX++;
                    currentY--;
                }
                if (clickedTile == null) {
                    if (isValidCombination(lowerRightTiles, whoseTurn)) {
                        currentEmptyTiles.get(i).setEnabled(true);
                    }//end lower right check
                }
                else {
                    setCombination(whoseTurn,lowerRightTiles,currentEmptyTiles.get(i));
                }
                //left check
                currentX = currentEmptyTiles.get(i).getxCoordinate()-1;
                currentY = currentEmptyTiles.get(i).getyCoordinate();
                ArrayList<Tile> leftTiles = new ArrayList<Tile>();
                while (currentX >= 0) {
                    if (emptyOrWhoseTurn(imageButtons[currentX][currentY],whoseTurn)) {
                        leftTiles.add(imageButtons[currentX][currentY]);
                        break;
                    }
                    else {
                        leftTiles.add(imageButtons[currentX][currentY]);
                    }
                    currentX--;
                }
                if (clickedTile == null) {
                    if (isValidCombination(leftTiles, whoseTurn)) {
                        currentEmptyTiles.get(i).setEnabled(true);
                    }// end left check
                }
                else {
                    setCombination(whoseTurn,leftTiles,currentEmptyTiles.get(i));
                }
                //right check
                currentX = currentEmptyTiles.get(i).getxCoordinate()+1;
                currentY = currentEmptyTiles.get(i).getyCoordinate();
                ArrayList<Tile> rightTiles = new ArrayList<Tile>();
                while (currentX < boardWidth) {
                    if (emptyOrWhoseTurn(imageButtons[currentX][currentY],whoseTurn)) {
                        rightTiles.add(imageButtons[currentX][currentY]);
                        break;
                    }
                    else {
                        rightTiles.add(imageButtons[currentX][currentY]);
                    }
                    currentX++;
                }
                if (clickedTile == null) {
                    if (isValidCombination(rightTiles, whoseTurn)) {
                        currentEmptyTiles.get(i).setEnabled(true);
                    }// end right check
                }
                else {
                    setCombination(whoseTurn,rightTiles,currentEmptyTiles.get(i));
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
                    enableValidTiles(currentButton);
                    disableTiles(boardWidth,boardHeight,imageButtons,whiteChip,grayChip,emptySlot);
                    moveCounter++;
                    if (moveCounter % 2 == 1) {
                        whoseTurn = "gray";
                    }
                    if (moveCounter % 2 == 0) {
                        whoseTurn = "white";
                    }
                    System.out.println("Now, it is " + whoseTurn + "'s turn!");
                    enableValidTiles(null);
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
        enableValidTiles( null);
    }//end board constructor
}
