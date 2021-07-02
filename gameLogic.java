import javax.swing.*;
import java.util.ArrayList;

public interface gameLogic { // part of the reason why I decided to make this interface is that I don't want to include
    //too many lines of code in the Board.java file.. Besides, this interface would be a good use of the abstraction principle
    //see stackoverflow.com/questions/5309260/in-java-can-we-divide-a-class-into-multiple-files
    ////www.geeksforgeeks.org/interfaces-in-java/
    // in OOP
    // with the default keyword, a method CAN have a body in an interface...

    default ArrayList<Tile> getUpperLeftTiles(Tile currentEmptyTile, int boardHeight, Tile[][] imageButtons,String whoseTurn) {
        //check upper left diagonal
        ArrayList<Tile> upperLeftTiles = new ArrayList<Tile>();
        int currentX = currentEmptyTile.getxCoordinate()-1;
        int currentY = currentEmptyTile.getyCoordinate()+1;
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
        return upperLeftTiles;
    }

    default ArrayList<Tile> getUpperTiles(Tile currentEmptyTile, int boardHeight, Tile[][] imageButtons,String whoseTurn) {
        ArrayList<Tile> upperTiles = new ArrayList<Tile>();
        //check upper
        int currentX = currentEmptyTile.getxCoordinate();
        int currentY = currentEmptyTile.getyCoordinate()+1;
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
        return upperTiles;
    }

    default ArrayList<Tile> getUpperRightTiles(Tile currentEmptyTile, int boardWidth, int boardHeight, Tile[][] imageButtons,String whoseTurn) {
        ArrayList<Tile> upperRightTiles = new ArrayList<Tile>();
        int currentX = currentEmptyTile.getxCoordinate()+1;
        int currentY = currentEmptyTile.getyCoordinate()+1;
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
        return upperRightTiles;
    }

    default ArrayList<Tile> getLowerLeftTiles(Tile currentEmptyTile, Tile[][] imageButtons,String whoseTurn) {
        int currentX = currentEmptyTile.getxCoordinate()-1;
        int currentY = currentEmptyTile.getyCoordinate()-1;
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
        return lowerLeftTiles;
    }

    default ArrayList<Tile> getLowerTiles(Tile currentEmptyTile, Tile[][] imageButtons,String whoseTurn) {
        int currentX = currentEmptyTile.getxCoordinate();
        int currentY = currentEmptyTile.getyCoordinate()-1;
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
        return lowerTiles;
    }

    default ArrayList<Tile> getLowerRightTiles(Tile currentEmptyTile, int boardWidth, Tile[][] imageButtons,String whoseTurn) {
        int currentX = currentEmptyTile.getxCoordinate()+1;
        int currentY = currentEmptyTile.getyCoordinate()-1;
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
        return lowerRightTiles;
    }

    default ArrayList<Tile> getLeftTiles(Tile currentEmptyTile, Tile[][] imageButtons,String whoseTurn) {
        int currentX = currentEmptyTile.getxCoordinate()-1;
        int currentY = currentEmptyTile.getyCoordinate();
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
        return leftTiles;
    }

    default ArrayList<Tile> getRightTiles(Tile currentEmptyTile, int boardWidth, Tile[][] imageButtons,String whoseTurn) {
        int currentX = currentEmptyTile.getxCoordinate()+1;
        int currentY = currentEmptyTile.getyCoordinate();
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
        return rightTiles;
    }

    default String getOppositeColor(String whoseTurn) {
        if (whoseTurn.equals("white")) {
            return "gray";
        }
        else {
            return "white";
        }
    }

    default void setCombination(String whoseTurn, Tile clickedTile) {
        // plan: get empty tile and check all directions without going out of bounds (upper left, up, down, etc.)
        // then set all the appropriate tiles as white or gray depending on whose move it is
        /*if (tileLine.size() >=2) { // fix this
            if (tileLine.get(tileLine.size()-1).getCurrentIcon().equals("empty")
                    && tileLine.get(tileLine.size()-2).getCurrentIcon().equals(getOppositeColor(whoseTurn))) {
                System.out.println("yo");
                return;
            }
            if (whoseTurn.equals("white")) {
                for (int i = 0; i < tileLine.size() - 1; i++) {
                        tileLine.get(i).setWhiteChip();
                }
                clickedTile.setWhiteChip();
            } else {
                for (int i = 0; i < tileLine.size() - 1; i++) {
                        tileLine.get(i).setGrayChip();
                }
                clickedTile.setGrayChip();
            }
        }*/
        //check upper left


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
