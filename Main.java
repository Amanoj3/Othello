import javax.swing.*;
import java.awt.*;

public class Main { // the driver file - also contains the view

    public static void setUp() {
        Board othelloBoard = new Board();
        JFrame window = new JFrame("Othello");
        JLabel[] xCoordinateLabels = new JLabel[8];
        int x = 115;
        int y = 450;
        JLabel[] yCoordinateLabels = new JLabel[8];
        for (int i = 0; i < 8; i++) {

            //initialize the labels
            xCoordinateLabels[i] = new JLabel(Integer.toString(i));
            yCoordinateLabels[i] = new JLabel(Integer.toString(i));

            //set font and size
            xCoordinateLabels[i].setFont(new Font("Arial", Font.BOLD, 20));
            yCoordinateLabels[i].setFont(new Font("Arial", Font.BOLD, 20));

            //set color of the labels (red in this case)
            xCoordinateLabels[i].setForeground(Color.RED);
            yCoordinateLabels[i].setForeground(Color.RED);

            //set the coordinates
            xCoordinateLabels[i].setBounds(x,500,50,50);
            yCoordinateLabels[i].setBounds(50, y, 50, 50);

            //increment/decrement for the next coordinate points
            x = x + 50;
            y = y - 50;

            //add the coordinates to the frame
            window.add(xCoordinateLabels[i]);
            window.add(yCoordinateLabels[i]);
        }
        //JLabel text = new JLabel("yo!");
        //text.setForeground(Color.RED);
        //text.setBounds(0,475,100,100);
        //JButton testButton = new JButton("yo!");
        //testButton.setBounds(0,0,50,50);
        //window.add(text);
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
