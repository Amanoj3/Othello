import javax.swing.*;
import java.util.ArrayList;

public interface gameLogic { // part of the reason why I decided to make this interface is that I don't want to include
    //too many lines of code in the Board.java file.. Besides, this interface would be a good use of the abstraction principle
    //see stackoverflow.com/questions/5309260/in-java-can-we-divide-a-class-into-multiple-files
    ////www.geeksforgeeks.org/interfaces-in-java/
    // in OOP
    // with the default keyword, a method CAN have a body in an interface...

    default void disableTiles(int boardWidth, int boardHeight, Tile[][] imageButtons, Icon white, Icon gray, Icon empty) {
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

    default boolean emptyOrWhite(Tile tile) {
        return tile.getCurrentIcon().equals("white") || tile.getCurrentIcon().equals("empty");
    }

    default boolean isValidCombination(ArrayList<Tile> tiles, String whoseTurn) {
        if (tiles.size() != 0) {
            if (tiles.get(tiles.size()-1).getCurrentIcon().equals(whoseTurn)){
                return tiles.size() >= 2;
            }
        }
        return false;
    }
}
