import javax.swing.*;
import java.util.ArrayList;

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

    default void setCombination(String whoseTurn, ArrayList<Tile> tileLine, Tile clickedTile) {
        // turns all the chips in a line into the color of the player's chip
        // for example, if there is a diagonal line of white chips, that line will become gray if the white player
        // plays such a move
        if (tileLine.size() >=2) { // fix this
            if (tileLine.get(tileLine.size()-1).getCurrentIcon().equals("empty")
                    && tileLine.get(tileLine.size()-2).getCurrentIcon().equals(getOppositeColor(whoseTurn))) {
                System.out.println("yo");
                return;
            }
            if (whoseTurn.equals("white")) {
                for (int i = 0; i < tileLine.size()-1; i++) {
                    tileLine.get(i).setWhiteChip();
                }
                clickedTile.setWhiteChip();
            } else {
                for (int i = 0; i < tileLine.size()-1; i++) {
                    tileLine.get(i).setGrayChip();
                }
                clickedTile.setGrayChip();
            }
        }
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
