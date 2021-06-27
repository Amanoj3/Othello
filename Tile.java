import javax.swing.*;

public class Tile extends JButton {
    private final Icon whiteChip;
    private final Icon grayChip;
    private String currentIcon;

    public String getCurrentIcon() {
        return this.currentIcon;
    }

    void setWhiteChip() {
        this.setIcon(whiteChip);
        currentIcon = "white";
    }

    void setGrayChip() {
        this.setIcon(grayChip);
        currentIcon = "gray";
    }

    Tile() {
        currentIcon = "empty";
        Icon emptySlot = new ImageIcon("src/othello_emptySlot.png");
        whiteChip = new ImageIcon("src/othello_whiteChip.png");
        grayChip = new ImageIcon("src/othello_grayChip.png");
        this.setIcon(emptySlot);
    }
}
