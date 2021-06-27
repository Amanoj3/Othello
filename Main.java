import javax.swing.*;
import java.awt.*;

public class Main { // the driver file - also contains the view

    public static void setUp() {
        Board othelloBoard = new Board();
        JFrame window = new JFrame("Othello");
        for (int i = 0; i < othelloBoard.getBoardWidth(); i++) {
            for (int j = 0; j < othelloBoard.getBoardHeight(); j++) {
                window.add(othelloBoard.getImageButtons()[i][j]);
            }
        }

        window.setSize(600,700);
        window.getContentPane().setBackground(Color.lightGray);
        window.setLayout(null);
        window.setVisible(true);
        window.setResizable(false);
    }

    public static void main(String[] args){
        setUp();

    }
}
