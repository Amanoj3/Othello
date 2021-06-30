import java.util.ArrayList;

public interface gameLogic { // part of the reason why I decided to make this interface is that I don't want to include
    //too many lines of code in the Board.java file.. Besides, this interface would be a good use of the abstraction principle
    // in OOP
    // with the default keyword, a method CAN have a body in an interface...
    default ArrayList<Tile> obtainEmptyTiles(Tile[][] imgButtons, int width, int height) {
        ArrayList<Tile> emptyTiles = new ArrayList<Tile>();
        for (int x = 0; x < width; x++) { // iterate through the tiles and add empty ones to the arraylist
            for (int y = 0; y < height; y++) {
                if (imgButtons[x][y].getCurrentIcon().equals("empty")) {
                    emptyTiles.add(imgButtons[x][y]);
                }
            }
        }
        return emptyTiles;
    }

    default public boolean emptyOrWhite(Tile tile) {
        return tile.getCurrentIcon().equals("white") || tile.getCurrentIcon().equals("empty");
    }

    default public boolean isValidCombination(ArrayList<Tile> tiles, String whoseTurn) {
        if (tiles.size() != 0) {
            if (tiles.get(tiles.size()-1).getCurrentIcon().equals(whoseTurn)){
                return tiles.size() >= 2;
            }
        }
        return false;
    }
}
