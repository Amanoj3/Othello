import javax.swing.*;

public class Tile extends JButton {
    private Icon emptySlot;
    private Icon whiteChip;
    private Icon grayChip;

    void setWhiteChip() {
        this.setIcon(whiteChip);
    }

    void setGrayChip() {
        this.setIcon(grayChip);
    }

    Tile() {
        emptySlot = new ImageIcon("src/othello_emptySlot.png");
        whiteChip = new ImageIcon("src/othello_whiteChip.png");
        grayChip = new ImageIcon("src/othello_grayChip.png");
        this.setIcon(emptySlot);
    }
}
