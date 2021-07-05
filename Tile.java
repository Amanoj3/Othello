import javax.swing.*;

public class Tile extends JButton {
    private final Icon whiteChip;
    private final Icon grayChip;
    private final Icon emptyChip;
    private String currentIcon;
    private final int xCoordinate;
    private final int yCoordinate; // so we can determine the location of the tile
    public String getCurrentIcon() {
        return this.currentIcon;
    }

    int getxCoordinate() {
        return this.xCoordinate;
    }

    int getyCoordinate() {
        return this.yCoordinate;
    }

    void setEmptyChip() {
        currentIcon = "empty";
        this.setIcon(emptyChip);
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
        emptyChip = new ImageIcon("src/othello_emptySlot.png");
        whiteChip = new ImageIcon("src/othello_whiteChip.png");
        grayChip = new ImageIcon("src/othello_grayChip.png");
        this.setIcon(emptyChip);
    }
}
