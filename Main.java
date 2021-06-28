import javax.swing.*;
import java.awt.*;

public class Main { // the driver file - also contains the view

    public static void setUp() {
        Board othelloBoard = new Board();
        JFrame window = new JFrame("Othello");
        Image frameLogo = new ImageIcon("src/othelloLogo.png").getImage(); //logo for the frame
        for (int i = 0; i < othelloBoard.getBoardWidth(); i++) {
            for (int j = 0; j < othelloBoard.getBoardHeight(); j++) {
                window.add(othelloBoard.getImageButtons()[i][j]);
            }
        }
        window.setIconImage(frameLogo);
        window.setSize(600,700);
        window.getContentPane().setBackground(Color.decode("#102b52")); // navy blue-ish
        window.setLayout(null);
        window.setVisible(true);
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // close the window to end the program
    }

    public static void main(String[] args){
        setUp();

    }
}
