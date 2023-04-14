import java.awt.*;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.*;

/**
 * Displays circles blinking on and off that are moved and resized with each
 * click of the screen
 */
public class Display extends JPanel {
    int choice; // represents which drawing is selected

    public Display() {
        choice = 0; // default

        setBackground(Color.YELLOW);
        setPreferredSize(new Dimension(700, 700));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // clear canvas

        // make it smoother:
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // thickness of line drawings (can change this before drawing different parts):
        g2.setStroke(new BasicStroke(4));

        // draw the stuff:
        switch (choice) {
            case 6:
                drawGraph(g2, new int[] { 40, 60, 60, 50 });
                break;
            case 5:
                drawQRCode(g2);
                break;
            case 4:
                drawCube(g2);
                break;

            case 3:
                drawSunrise(g2);
                // draw a sunset...
                break;

            case 2:
                drawRings(g2);
                break;
            case 1:
                drawEmoji(g2);
                break;

            default:
                g.setColor(Color.GRAY);
                g.drawOval(200, 200, 200, 200); // x, y, w, h
                g.drawString("(will draw some image here)", 50, 50);
                break;

        }
    }

    private static void drawCube(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);

        g2d.fill3DRect(100, 100, 200, 200, false);
        g2d.fill3DRect(400, 400, 200, 200, false);

        Polygon topSide = new Polygon(new int[] { 300, 100, 400, 600 }, new int[] {
                100, 100, 400, 400 }, 4);

        Polygon bottomSide = new Polygon(new int[] { 300, 100, 400, 600 }, new int[] { 300, 300, 600, 600 }, 4);

        Polygon leftSide = new Polygon(new int[] { 400, 100, 100, 400 }, new int[] {
                400, 100, 300, 600 }, 4);
        g2d.drawPolygon(leftSide);

        Polygon rightSide = new Polygon(new int[] { 300, 300, 600, 600 }, new int[] { 300, 100, 400, 600 }, 4);
        g2d.fillPolygon(rightSide);
        g2d.fillPolygon(bottomSide);
        g2d.fillPolygon(topSide);
        g2d.fillPolygon(leftSide);

    }

    private void drawSunrise(Graphics2D g2d) {
        setBackground(Color.BLUE);
        Dimension size = getSize();

        g2d.setColor(Color.yellow);
        g2d.fillOval(50, 50, 100, 100);

        for (int i = 0; i < 10; i++) {

            int newYPos = 50 * i;

            g2d.setColor(new Color(0, 255, i * 15));
            g2d.fill3DRect(0, size.height - newYPos, size.width, 50, false);

        }

    }

    private void drawRings(Graphics2D g2d) {
        Dimension size = getSize();

        for (int i = 10; i > 0; i--) {
            int newRadius = 50 * i;
            g2d.setColor(
                    new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
            g2d.fillOval((size.width - newRadius) / 2, (size.height - newRadius) / 2, 50 * i, 50 * i);

        }
    }

    private void drawEmoji(Graphics2D g2d) {
        setBackground(Color.WHITE);
        g2d.setColor(Color.YELLOW);
        Dimension size = getSize();
        int radius = 350;

        g2d.fillOval((size.width - radius) / 2, (size.height - radius) / 2, radius, radius);

        g2d.setColor(Color.WHITE);
        g2d.fillOval((radius - 100), (radius - 100), 50, 50);
        g2d.fillOval((radius + 50), (radius - 100), 50, 50);
        g2d.setColor(Color.WHITE);

        g2d.fillOval(radius - 50, radius, 100, 100);
    }

    private void drawQRCode(Graphics2D g2d) {
        Dimension size = getSize();

        setBackground(Color.WHITE);
        g2d.setColor(Color.BLACK);

        // Top Left Square
        g2d.fillRect(125, 125, 50, 50);
        g2d.drawRect(100, 100, 100, 100);
        renderTopRects(g2d);

        // Top Right Square
        g2d.fillRect(size.width - 125, 125, 50, 50);
        g2d.drawRect(size.width - 150, 100, 100, 100);

        // Bottom Left Square
        g2d.fillRect(150, size.height - 225, 50, 50);
        g2d.drawRect(125, size.height - 250, 100, 100);
        renderLeftRects(g2d);
        renderBottomRects(g2d);
        renderRightRects(g2d);

        for (int i = 0; i < 45; i++) {
            g2d.setBackground(Color.black);
            int x = (int) (Math.random() * 250) + 230;
            int y = (int) (Math.random() * 200) + 200;

            g2d.fillRect(x, y, 35, 35);

        }
    }

    private void renderTopRects(Graphics2D g2d) {
        for (int i = 0; i < 50; i++) {
            int x = (int) (Math.random() * 275) + 205;
            int y = (int) (Math.random() * 75) + 100;

            g2d.fillRect(x, y, 25, 25);

        }

    }

    private void renderLeftRects(Graphics2D g2d) {
        for (int i = 0; i < 45; i++) {
            int x = (int) (Math.random() * 115) + 95;

            int y = (int) (Math.random() * 170) + 215;
            g2d.fillRect(x, y, 25, 25);

        }
    }

    private void renderRightRects(Graphics2D g2d) {
        Dimension size = getSize();

        for (int i = 0; i < 25; i++) {
            int x = (int) (Math.random() * 95) + (size.width - 195);

            int y = (int) (Math.random() * 300) + (size.height - 475);
            g2d.fillRect(x, y, 25, 25);

        }
    }

    private void renderBottomRects(Graphics2D g2d) {
        for (int i = 0; i < 35; i++) {
            Dimension size = getSize();

            g2d.setBackground(Color.black);
            int x = (int) (Math.random() * 250) + (size.width - 450);
            int y = (int) (Math.random() * 100) + (size.height - 250);

            g2d.fillRect(x, y, 35, 35);

        }
    }

    private void drawGraph(Graphics2D g2d, int[] nums) {
        Dimension size = getSize();
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        int[] percentages = new int[4];

        for (int i = 0; i < percentages.length; i++) {

            percentages[i] = (int) (nums[i] * 100) / sum;

        }
        int startingDegrees = 0;
        int[] starting_pos = new int[] { 150, 50, 500, 350 };
        // X1 Y1, X2, Y2
        for (int percent : percentages)
            g2d.setColor(Color.blue);
        // g2d.fillArc(150,50,350,300,)

        g2d.setColor(Color.green);
        g2d.fillArc((size.width - 400) / 2, (size.height - 400) / 2, 400, 400, 123, percentages[1] * 360);

        g2d.setColor(Color.orange);
        g2d.fillArc((size.width - 400) / 2, (size.height - 400) / 2, 400, 400, 45, percentages[2] * 360);

        g2d.setColor(Color.CYAN);
        g2d.fillArc((size.width - 400) / 2, (size.height - 400) / 2, 400, 400, 255, percentages[3] * 360);

        // g2d.fillArc((size.width - 400) / 2, (size.height - 400) / 2, 400, 400, 0,
        // 45);

        // g2d.setColor(Color.blue);
        // g2d.fillArc((size.width - 400) / 2, (size.height - 400) / 2, 400, 400, 123,
        // 135);

        // g2d.setColor(Color.orange);
        // g2d.fillArc((size.width - 400) / 2, (size.height - 400) / 2, 400, 400, 45,
        // 90);

        // g2d.setColor(Color.CYAN);
        // g2d.fillArc((size.width - 400) / 2, (size.height - 400) / 2, 400, 400, 255,
        // 110);
    }
}
