import javax.swing.JOptionPane;

public class Runner {
    public static void main(String args[]) {

        Window win = new Window(); // the JFrame, which creates a Display object

        int choice = -1;

        while (choice != 0) {
            // allow user to choose what to draw:
            String[] options = { "Quit", "Emoji", "Rings", "Sunset", "Cube", "Pie-Chart", "QR-Code" }; // opposite order
                                                                                                       // in buttons

            choice = JOptionPane.showOptionDialog(null,
                    "Choose image to generate", // message in frame
                    "Images", // text in titlebar
                    JOptionPane.YES_NO_CANCEL_OPTION, // 3-button joptionpane
                    JOptionPane.QUESTION_MESSAGE, // sets icon type
                    null, // would use for user icon
                    options, // list of options to appear on buttons
                    options[options.length - 1]); // specified which button is selected as default

            // set the choihce in the display object:
            win.display.choice = choice;

            // show that result:
            win.display.repaint();
        }
        win.dispose();
        System.exit(0);
    }
}
