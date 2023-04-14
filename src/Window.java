import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Window extends JFrame {
    Display display; // a JPanel with paintComponent()

    public Window() {

        display = new Display();

        this.add(display); // add to this JFrame, using the default layout manager

        this.setPreferredSize(new Dimension(700, 700));

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack(); // place and size all components that were add()ed to this JFrame

        this.setVisible(true);
    }

}
