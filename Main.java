import javax.swing.*;
import java.awt.*;

public class Main {

    public static void setUp() {
        JFrame window = new JFrame();
        window.setSize(500,500);
        window.setLayout(null);
        window.setVisible(true);
        window.setResizable(false);
    }

    public static void main(String[] args){
        setUp();
        System.out.println("foo");

    }
}
