import javax.swing.*;
import java.awt.*;

public class Board {
    private int numSlots;
    private int boardWidth;
    private int boardHeight;
    private Tile[][] imageButtons;
    private Icon emptySlot;
    private Icon whiteChip;
    private Icon grayChip;

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
            for (int j = 0; j < boardHeight; j++) {
                Tile currentButton = new Tile();
                imageButtons[i][j] = currentButton;
                imageButtons[i][j].setBounds(startingX, startingY, 50, 50);
                startingY = startingY - incrementFactorY;
            }
            startingX = startingX + incrementFactorX;
        }
    }
}
