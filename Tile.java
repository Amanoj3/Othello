import javax.swing.*;

public class Tile extends JButton {
    private final Icon whiteChip;
    private final Icon grayChip;
    private String currentIcon;
    private int xCoordinate, yCoordinate; // so we can determine the location of the tile
    public String getCurrentIcon() {
        return this.currentIcon;
    }

    int getxCoordinate() {
        return this.xCoordinate;
    }

    int getyCoordinate() {
        return this.yCoordinate;
    }

    void setWhiteChip() {
        this.setIcon(whiteChip);
        currentIcon = "white";
    }

    void setGrayChip() {
        this.setIcon(grayChip);
        currentIcon = "gray";
    }

    Tile(int x, int y) {
        xCoordinate = x;
        yCoordinate = y;
        currentIcon = "empty";
        Icon emptySlot = new ImageIcon("src/othello_emptySlot.png");
        whiteChip = new ImageIcon("src/othello_whiteChip.png");
        grayChip = new ImageIcon("src/othello_grayChip.png");
        this.setIcon(emptySlot);
    }
}
