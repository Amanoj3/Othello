import javax.swing.*;
import java.awt.image.renderable.RenderableImage;
import java.util.ArrayList;
//TO DO (AS OF 7/3/21 11:05 PM):
// TAKE INTO ACCOUNT THE SCENARIO WHERE PLAYER X DOESN'T HAVE ANY VALID SQUARES (MOVES)
public interface gameLogic { // part of the reason why I decided to make this interface is that I don't want to include
    //too many lines of code in the Board.java file.. Besides, this interface would be a good use of the abstraction principle
    //see stackoverflow.com/questions/5309260/in-java-can-we-divide-a-class-into-multiple-files
    ////www.geeksforgeeks.org/interfaces-in-java/
    // in OOP
    // with the default keyword, a method CAN have a body in an interface...

    default String getOppositeColor(String whoseTurn) {
        if (whoseTurn.equals("white")) {
            return "gray";
        }
        else {
            return "white";
        }
    }

    default void setChips(ArrayList<Tile> tiles, String whoseTurn) {
        if (tiles.get(0).getCurrentIcon().equals(tiles.get(tiles.size() - 1).getCurrentIcon())) {
            for (Tile tile : tiles) {
                if (whoseTurn.equals("white")) {
                    tile.setWhiteChip();
                } else {
                    tile.setGrayChip();
                }
            }
        }
    }

    default ArrayList<Tile> getUpperLeftTiles(Tile currentEmptyTile, int boardHeight, Tile[][] imageButtons,String whoseTurn, boolean setMode) {
        //check upper left diagonal
        ArrayList<Tile> upperLeftTiles = new ArrayList<Tile>();
        int currentX = currentEmptyTile.getxCoordinate()-1;
        int currentY = currentEmptyTile.getyCoordinate()+1;
        while (currentX >=0 && currentY < boardHeight) {
            if (emptyOrWhoseTurn(imageButtons[currentX][currentY],whoseTurn)) {
                if (!setMode) {
                    upperLeftTiles.add(imageButtons[currentX][currentY]);
                }
                break;
            }
            else {
                upperLeftTiles.add(imageButtons[currentX][currentY]);
            }
            currentX--;
            currentY++;
        }
        return upperLeftTiles;
    }

    default ArrayList<Tile> getUpperTiles(Tile currentEmptyTile, int boardHeight, Tile[][] imageButtons,String whoseTurn, boolean setMode) {
        ArrayList<Tile> upperTiles = new ArrayList<Tile>();
        //check upper
        int currentX = currentEmptyTile.getxCoordinate();
        int currentY = currentEmptyTile.getyCoordinate()+1;
        while (currentY < boardHeight) {
            if (emptyOrWhoseTurn(imageButtons[currentX][currentY],whoseTurn)) {
                if (!setMode) {
                    upperTiles.add(imageButtons[currentX][currentY]);
                }
                break;
            }
            else {
                upperTiles.add(imageButtons[currentX][currentY]);
            }
            currentY++;
        }
        return upperTiles;
    }

    default ArrayList<Tile> getUpperRightTiles(Tile currentEmptyTile, int boardWidth, int boardHeight, Tile[][] imageButtons,String whoseTurn, boolean setMode) {
        ArrayList<Tile> upperRightTiles = new ArrayList<Tile>();
        int currentX = currentEmptyTile.getxCoordinate()+1;
        int currentY = currentEmptyTile.getyCoordinate()+1;
        while (currentX < boardWidth && currentY < boardHeight) {
            if (emptyOrWhoseTurn(imageButtons[currentX][currentY],whoseTurn)) {
                if (!setMode) {
                    upperRightTiles.add(imageButtons[currentX][currentY]);
                }
                break;
            }
            else {
                upperRightTiles.add(imageButtons[currentX][currentY]);
            }
            currentX++;
            currentY++;
        }
        return upperRightTiles;
    }

    default ArrayList<Tile> getLowerLeftTiles(Tile currentEmptyTile, Tile[][] imageButtons,String whoseTurn, boolean setMode) {
        int currentX = currentEmptyTile.getxCoordinate()-1;
        int currentY = currentEmptyTile.getyCoordinate()-1;
        ArrayList<Tile> lowerLeftTiles = new ArrayList<Tile>();
        while (currentX >= 0 && currentY >=0) {
            if (emptyOrWhoseTurn(imageButtons[currentX][currentY],whoseTurn)) {
                if (!setMode) {
                    lowerLeftTiles.add(imageButtons[currentX][currentY]);
                }
                break;
            }
            else {
                lowerLeftTiles.add(imageButtons[currentX][currentY]);
            }
            currentX--;
            currentY--;
        }
        return lowerLeftTiles;
    }

    default ArrayList<Tile> getLowerTiles(Tile currentEmptyTile, Tile[][] imageButtons,String whoseTurn, boolean setMode) {
        int currentX = currentEmptyTile.getxCoordinate();
        int currentY = currentEmptyTile.getyCoordinate()-1;
        ArrayList<Tile> lowerTiles = new ArrayList<Tile>();
        while (currentY >= 0) {
            if (emptyOrWhoseTurn(imageButtons[currentX][currentY],whoseTurn)) {
                if (!setMode) {
                    lowerTiles.add(imageButtons[currentX][currentY]);
                }
                break;
            }
            else {
                lowerTiles.add(imageButtons[currentX][currentX]);
            }
            currentY--;
        }
        return lowerTiles;
    }

    default ArrayList<Tile> getLowerRightTiles(Tile currentEmptyTile, int boardWidth, Tile[][] imageButtons,String whoseTurn, boolean setMode) {
        int currentX = currentEmptyTile.getxCoordinate()+1;
        int currentY = currentEmptyTile.getyCoordinate()-1;
        ArrayList<Tile> lowerRightTiles = new ArrayList<Tile>();
        while (currentX < boardWidth && currentY >=0) {
            if (emptyOrWhoseTurn(imageButtons[currentX][currentY],whoseTurn)) {
                if (!setMode) {
                    lowerRightTiles.add(imageButtons[currentX][currentY]);
                }
                break;
            }
            else {
                lowerRightTiles.add(imageButtons[currentX][currentY]);
            }
            currentX++;
            currentY--;
        }
        return lowerRightTiles;
    }

    default ArrayList<Tile> getLeftTiles(Tile currentEmptyTile, Tile[][] imageButtons,String whoseTurn, boolean setMode) {
        int currentX = currentEmptyTile.getxCoordinate()-1;
        int currentY = currentEmptyTile.getyCoordinate();
        ArrayList<Tile> leftTiles = new ArrayList<Tile>();
        while (currentX >= 0) {
            if (emptyOrWhoseTurn(imageButtons[currentX][currentY],whoseTurn)) {
                if (!setMode) {
                    leftTiles.add(imageButtons[currentX][currentY]);
                }
                break;
            }
            else {
                leftTiles.add(imageButtons[currentX][currentY]);
            }
            currentX--;
        }
        return leftTiles;
    }

    default ArrayList<Tile> getRightTiles(Tile currentEmptyTile, int boardWidth, Tile[][] imageButtons,String whoseTurn, boolean setMode) {
        int currentX = currentEmptyTile.getxCoordinate()+1;
        int currentY = currentEmptyTile.getyCoordinate();
        ArrayList<Tile> rightTiles = new ArrayList<Tile>();
        while (currentX < boardWidth) {
            if (emptyOrWhoseTurn(imageButtons[currentX][currentY],whoseTurn)) {
                if (!setMode) {
                    rightTiles.add(imageButtons[currentX][currentY]);
                }
                break;
            }
            else {
                rightTiles.add(imageButtons[currentX][currentY]);
            }
            currentX++;
        }
        return rightTiles;
    }
    // combo section below
    default void lowerCombo(Tile clickedTile, Tile[][] imageButtons, String whoseTurn) {
        ArrayList<Tile> lowerTiles = new ArrayList<Tile>();
        lowerTiles.add(clickedTile);

        int curY = lowerTiles.get(0).getyCoordinate()-1;
        int curX = lowerTiles.get(0).getxCoordinate();
        boolean oppMode = false;
        while (curY >= 0) {
            Tile currentTile = imageButtons[curX][curY];
            if (!oppMode && emptyOrWhoseTurn(currentTile,whoseTurn)) {
                break;
            }
            if (currentTile.getCurrentIcon().equals(getOppositeColor(whoseTurn))) {
                oppMode = true;
                lowerTiles.add(currentTile);
                curY--;
                continue;
            }
            if (oppMode && emptyOrWhoseTurn(currentTile,whoseTurn)) {
                lowerTiles.add(currentTile);
                break;
            }
            curY--;
        }
        setChips(lowerTiles,whoseTurn);
    }

    default void lowerRightCombo(Tile clickedTile, Tile[][] imageButtons, String whoseTurn) {
        int curX = clickedTile.getxCoordinate()+1;
        int curY = clickedTile.getyCoordinate()-1;
        ArrayList<Tile> lowerRightTiles = new ArrayList<Tile>();
        lowerRightTiles.add(clickedTile);
        boolean oppMode = false;
        while (curX < 8 && curY >=0) {
            Tile currentTile = imageButtons[curX][curY];
            if (!oppMode && emptyOrWhoseTurn(currentTile,whoseTurn)) {
                break;
            }
            if (currentTile.getCurrentIcon().equals(getOppositeColor(whoseTurn))) {
                oppMode = true;
                lowerRightTiles.add(currentTile);
                curY--;
                curX++;
                continue;
            }
            if (oppMode && emptyOrWhoseTurn(currentTile,whoseTurn)) {
                lowerRightTiles.add(currentTile);
                break;
            }
            curY--;
            curX++;
        }
        setChips(lowerRightTiles,whoseTurn);
    }

    default void lowerLeftCombo(Tile clickedTile, Tile[][] imageButtons, String whoseTurn) {
        int curX = clickedTile.getxCoordinate()-1;
        int curY = clickedTile.getyCoordinate()-1;
        ArrayList<Tile> lowerLeftTiles = new ArrayList<Tile>();
        lowerLeftTiles.add(clickedTile);
        boolean oppMode = false;
        while (curX >= 0 && curY >=0) {
            Tile currentTile = imageButtons[curX][curY];
            if (!oppMode && emptyOrWhoseTurn(currentTile,whoseTurn)) {
                break;
            }
            if (currentTile.getCurrentIcon().equals(getOppositeColor(whoseTurn))) {
                oppMode = true;
                lowerLeftTiles.add(currentTile);
                curY--;
                curX--;
                continue;
            }
            if (oppMode && emptyOrWhoseTurn(currentTile,whoseTurn)) {
                lowerLeftTiles.add(currentTile);
                break;
            }
            curY--;
            curX--;
        }
        setChips(lowerLeftTiles,whoseTurn);
    }

    default void upperRightCombo(Tile clickedTile, Tile[][] imageButtons, String whoseTurn) {
        ArrayList<Tile> upperRightTiles = new ArrayList<Tile>();
        int curX = clickedTile.getxCoordinate()+1;
        int curY = clickedTile.getyCoordinate()+1;
        upperRightTiles.add(clickedTile);
        boolean oppMode = false;
        while (curX < 8 && curY < 8) {
            Tile currentTile = imageButtons[curX][curY];
            if (!oppMode && emptyOrWhoseTurn(currentTile,whoseTurn)) {
                break;
            }
            if (currentTile.getCurrentIcon().equals(getOppositeColor(whoseTurn))) {
                oppMode = true;
                upperRightTiles.add(currentTile);
                curY++;
                curX++;
                continue;
            }
            if (oppMode && emptyOrWhoseTurn(currentTile,whoseTurn)) {
                upperRightTiles.add(currentTile);
                break;
            }
            curY++;
            curX++;
        }
        setChips(upperRightTiles,whoseTurn);
    }

    default void upperCombo(Tile clickedTile, Tile[][] imageButtons, String whoseTurn) {
        ArrayList<Tile> upperTiles = new ArrayList<Tile>();
        upperTiles.add(clickedTile);
        int curY = upperTiles.get(0).getyCoordinate()+1;
        int curX = upperTiles.get(0).getxCoordinate();
        boolean oppMode = false;
        while (curY < 8) {
            Tile currentTile = imageButtons[curX][curY];
            if (!oppMode && emptyOrWhoseTurn(currentTile,whoseTurn)) {
                break;
            }
            if (currentTile.getCurrentIcon().equals(getOppositeColor(whoseTurn))) {
                oppMode = true;
                upperTiles.add(currentTile);
                curY++;
                continue;
            }
            if (oppMode && emptyOrWhoseTurn(currentTile,whoseTurn)) {
                upperTiles.add(currentTile);
                break;
            }
            curY++;
        }
        setChips(upperTiles,whoseTurn);
    }

    default void upperLeftCombo(Tile clickedTile, Tile[][] imageButtons, String whoseTurn) {
        ArrayList<Tile> upperLeftTiles = new ArrayList<Tile>();
        int curX = clickedTile.getxCoordinate()-1;
        int curY = clickedTile.getyCoordinate()+1;
        upperLeftTiles.add(clickedTile);
        boolean oppMode = false;
        while (curX >=0 && curY < 8) {
            Tile currentTile = imageButtons[curX][curY];
            if (!oppMode && emptyOrWhoseTurn(currentTile,whoseTurn)) {
                break;
            }
            if (currentTile.getCurrentIcon().equals(getOppositeColor(whoseTurn))) {
                oppMode = true;
                upperLeftTiles.add(currentTile);
                curY++;
                curX--;
                continue;
            }
            if (oppMode && emptyOrWhoseTurn(currentTile,whoseTurn)) {
                upperLeftTiles.add(currentTile);
                break;
            }
            curY++;
            curX--;
        }
        setChips(upperLeftTiles,whoseTurn);
    }

    default void leftCombo(Tile clickedTile, Tile[][] imageButtons, String whoseTurn) {
        int curX = clickedTile.getxCoordinate()-1;
        int curY = clickedTile.getyCoordinate();
        ArrayList<Tile> leftTiles = new ArrayList<Tile>();
        leftTiles.add(clickedTile);
        boolean oppMode = false;
        while (curX >= 0) {
            Tile currentTile = imageButtons[curX][curY];
            if (!oppMode && emptyOrWhoseTurn(currentTile,whoseTurn)) {
                break;
            }
            if (currentTile.getCurrentIcon().equals(getOppositeColor(whoseTurn))) {
                oppMode = true;
                leftTiles.add(currentTile);
                curX--;
                continue;
            }
            if (oppMode && emptyOrWhoseTurn(currentTile,whoseTurn)) {
                leftTiles.add(currentTile);
                break;
            }
            curX--;
        }
        setChips(leftTiles,whoseTurn);
    }

    default void rightCombo(Tile clickedTile, Tile[][] imageButtons, String whoseTurn) {
        int curX = clickedTile.getxCoordinate()+1;
        int curY = clickedTile.getyCoordinate();
        ArrayList<Tile> rightTiles = new ArrayList<Tile>();
        rightTiles.add(clickedTile);
        boolean oppMode = false;
        while (curX < 8) {
            Tile currentTile = imageButtons[curX][curY];
            if (!oppMode && emptyOrWhoseTurn(currentTile,whoseTurn)) {
                break;
            }
            if (currentTile.getCurrentIcon().equals(getOppositeColor(whoseTurn))) {
                oppMode = true;
                rightTiles.add(currentTile);
                curX++;
                continue;
            }
            if (oppMode && emptyOrWhoseTurn(currentTile,whoseTurn)) {
                rightTiles.add(currentTile);
                break;
            }
            curX++;
        }
        setChips(rightTiles,whoseTurn);
    }

    default void setCombination(String whoseTurn, Tile clickedTile, Tile[][] imageButtons) {

        if (whoseTurn.equals("white")) {
            clickedTile.setWhiteChip();
        }
        else {
            clickedTile.setGrayChip();
        }
        //lower combinations
        lowerCombo(clickedTile,imageButtons,whoseTurn);
        lowerRightCombo(clickedTile,imageButtons,whoseTurn);
        lowerLeftCombo(clickedTile,imageButtons,whoseTurn);
        //upper combinations
        upperRightCombo(clickedTile,imageButtons,whoseTurn);
        upperCombo(clickedTile,imageButtons,whoseTurn);
        upperLeftCombo(clickedTile,imageButtons,whoseTurn);
        //horizontal combinations
        leftCombo(clickedTile,imageButtons,whoseTurn);
        rightCombo(clickedTile,imageButtons,whoseTurn);
    }

    default void disableTiles(int boardWidth, int boardHeight, Tile[][] imageButtons, Icon white, Icon gray, Icon empty) {
        // this method disables all the tiles (so they won't be clickable)
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                if (imageButtons[i][j].getCurrentIcon().equals("white")) {
                    imageButtons[i][j].setDisabledIcon(white);
                }
                else if (imageButtons[i][j].getCurrentIcon().equals("gray")) {
                    imageButtons[i][j].setDisabledIcon(gray);
                }
                else {
                    imageButtons[i][j].setDisabledIcon(empty);
                }
                imageButtons[i][j].setEnabled(false);
            }
        }
    }

    default ArrayList<Tile> obtainEmptyTiles(Tile[][] imgButtons, int width, int height) {
        // obtain each tile that does not have a chip on it
        ArrayList<Tile> emptyTiles = new ArrayList<>();
        for (int x = 0; x < width; x++) { // iterate through the tiles and add empty ones to the arraylist
            for (int y = 0; y < height; y++) {
                if (imgButtons[x][y].getCurrentIcon().equals("empty")) {
                    emptyTiles.add(imgButtons[x][y]);
                }
            }
        }
        return emptyTiles;
    }

    default boolean emptyOrWhoseTurn(Tile tile,String whoseTurn) {
        // if the slot is empty or has a white chip..
        return tile.getCurrentIcon().equals(whoseTurn) || tile.getCurrentIcon().equals("empty");
    }

    default boolean isValidCombination(ArrayList<Tile> tiles, String whoseTurn) {
        if (tiles.size() != 0) { // first you have to guard against null cases, lest an exception gets thrown
            if (tiles.get(tiles.size()-1).getCurrentIcon().equals(whoseTurn)){ // if the last tile is white or gray..
                return tiles.size() >= 2;
            }
        }
        return false;
    }

}
