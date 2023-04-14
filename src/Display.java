import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;

import java.util.List;

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
                drawQRCode(g2);
                break;
            case 5:
                drawGraph(g2, new int[] { 30, 20, 10, 40 });
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

    /**
     * drawCube
     * 
     * @param g2d graphics to draw cube
     */
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

    /**
     * 
     * @param g2d graphics to draw sunrise
     */
    private void drawSunrise(Graphics2D g2d) {
        setBackground(Color.BLUE);
        Dimension size = getSize();

        g2d.setColor(Color.yellow);
        g2d.fillOval(50, 50, 100, 100);

        // Draws the grass rectangles
        for (int i = 0; i < 10; i++) {

            int newYPos = 50 * i;

            g2d.setColor(new Color(0, 255, i * 15));
            g2d.fill3DRect(0, size.height - newYPos, size.width, 50, false);

        }

    }

    /**
     * 
     * @param g2d graphics to draw rings
     */
    private void drawRings(Graphics2D g2d) {
        Dimension size = getSize();

        for (int i = 10; i > 0; i--) {
            int newRadius = 50 * i;
            g2d.setColor(
                    new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));

            // center is substracted by increasing radius to remain centered
            g2d.fillOval((size.width - newRadius) / 2, (size.height - newRadius) / 2, 50 * i, 50 * i);

        }
    }

    /**
     * Draws an emoji with a shocked expression
     * 
     * @param g2d graphics to draw rings
     */
    private void drawEmoji(Graphics2D g2d) {
        setBackground(Color.WHITE);
        g2d.setColor(Color.YELLOW);
        Dimension size = getSize();
        int radius = 350;

        g2d.fillOval((size.width - radius) / 2, (size.height - radius) / 2, radius, radius);

        // Draws Eyes
        g2d.setColor(Color.WHITE);
        g2d.fillOval((radius - 100), (radius - 100), 50, 50);
        g2d.fillOval((radius + 50), (radius - 100), 50, 50);
        g2d.setColor(Color.WHITE);

        g2d.fillOval(radius - 50, radius, 100, 100);
    }

    /**
     * Draws a QR code, though it cannot be scanned to access webpage
     * 
     * @param g2d
     */
    private void drawQRCode(Graphics2D g2d) {
        Dimension size = getSize();

        setBackground(Color.WHITE);
        g2d.setColor(Color.BLACK);

        // Top Left Square
        g2d.fillRect(125, 125, 50, 50);
        g2d.drawRect(100, 100, 100, 100);

        // Top Right Square
        g2d.fillRect(size.width - 125, 125, 50, 50);
        g2d.drawRect(size.width - 150, 100, 100, 100);

        // Bottom Left Square
        g2d.fillRect(150, size.height - 225, 50, 50);
        g2d.drawRect(125, size.height - 250, 100, 100);

        // Render Top Rects
        renderRects(215, 100, 13, 5, g2d);

        // Render Middle Rects
        renderRects(115, 225, 22, 7, g2d);

        // Render Bottom Rects
        renderRects(235, size.height - 265, 18, 5, g2d);
    }

    /**
     * 
     * @param xPosGrid top left corner of grid (x position only)
     * @param yPosGrid top left corner of grid (y position only)
     * @param numCols  number of columns
     * @param numRows  number of rows
     * @param g2d      required graphics2D object to draw rectangles
     */
    private void renderRects(int xPosGrid, int yPosGrid, int numCols, int numRows, Graphics2D g2d) {

        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {

                int x = (5 * i * 5) + xPosGrid;
                int y = (5 * j * 5) + yPosGrid;

                // Determines if square will be drawn
                if ((int) ((Math.random() * 2) + 1) == 2)
                    g2d.fillRect(x, y, 15, 15);

            }

        }
        // for (int i = 0; i < 50; i++) {
        // int x = (int) (Math.random() * 275) + 205;
        // int y = (int) (Math.random() * 75) + 100;

        // g2d.fillRect(x, y, 25, 25);

        // }

    }

    /*
     * private void renderLeftRects(Graphics2D g2d) {
     * // for (int i = 0; i < 45; i++) {
     * // int x = (int) (Math.random() * 115) + 95;
     * 
     * // int y = (int) (Math.random() * 170) + 215;
     * // g2d.fillRect(x, y, 25, 25);
     * 
     * // }
     * 
     * for (int row = 0; row < 6; row++) {
     * for (int col = 0; col < 6; col++) {
     * int x = 50 + col;
     * int y = 50 + col;
     * 
     * g2d.fillRect(x, y, 25, 25);
     * }
     * }
     * }
     * 
     * private void renderRightRects(Graphics2D g2d) {
     * Dimension size = getSize();
     * 
     * for (int i = 0; i < 25; i++) {
     * int x = (int) (Math.random() * 95) + (size.width - 195);
     * 
     * int y = (int) (Math.random() * 300) + (size.height - 475);
     * g2d.fillRect(x, y, 25, 25);
     * 
     * }
     * }
     * 
     * private void renderBottomRects(Graphics2D g2d) {
     * for (int i = 0; i < 35; i++) {
     * Dimension size = getSize();
     * 
     * g2d.setBackground(Color.black);
     * int x = (int) (Math.random() * 250) + (size.width - 450);
     * int y = (int) (Math.random() * 100) + (size.height - 250);
     * 
     * g2d.fillRect(x, y, 35, 35);
     * 
     * }
     * }
     */
    private Color getRandomColor(List<Color> colors) {
        Color selectedColor = colors.get((int) (Math.random() * colors.size()));
        colors.remove(selectedColor);
        return selectedColor;
    }

    /**
     * 
     * @param nums data values for pie chart
     * @return sum of numbers
     */
    private int getSum(int[] nums) {
        // Get sum
        int sum = 0;
        for (int num : nums)
            sum += num;

        return sum;
    }

    /*
     * private ArrayList<Integer> getPercentages(int sum, int[] nums) {
     * ArrayList<Integer> percentages = new ArrayList<Integer>();
     * 
     * // Calculate percentages
     * for (int i = 0; i < nums.length; i++)
     * percentages.add((int) (nums[i] * 100) / sum);
     * return percentages;
     * }
     */

    /**
     * 
     * @param nums   data-values
     * @param colors array of colors
     * @param sum    sum of data values
     * @param g2d    graphics for drawing arcs
     */
    private void drawPieChart(int[] nums, List<Color> colors, int sum, Graphics2D g2d) {
        Dimension size = getSize();

        int arcCenterX = (size.width / 2);
        int arcCenterY = (size.height / 2);

        int radius = 100;
        int startAngle = 0;

        for (int num : nums) {

            int arcAngle = (360 * num / sum);

            g2d.setColor(getRandomColor(colors));

            // minus radius keeps it centered

            g2d.fillArc(arcCenterX - radius, arcCenterY - radius, radius * 2, radius * 2, startAngle, arcAngle);
            startAngle += arcAngle;
        }
    }

    /**
     * Draws a pie chart with random colors
     * 
     * @param g2d  graphics
     * @param nums data-points
     */
    private void drawGraph(Graphics2D g2d, int[] nums) {

        List<Color> colors = new ArrayList<>();
        colors.add(Color.green);
        colors.add(Color.blue);
        colors.add(Color.red);
        colors.add(Color.pink);
        colors.add(Color.orange);

        int sum = getSum(nums);

        drawPieChart(nums, colors, sum, g2d);

        /*
         * // int[] starting_pos = new int[] { 150, 50, 500, 350 };
         * // X1 Y1, X2, Y2
         * 
         * // for (int percent : percentages)
         * // g2d.setColor(Color.blue);
         * // g2d.fillArc(150,50,350,300,)
         * 
         * // g2d.fillArc((size.width - 400) / 2, (size.height - 400) / 2, 400, 400, 0,
         * // 45);
         * 
         * // g2d.setColor(Color.blue);
         * // g2d.fillArc((size.width - 400) / 2, (size.height - 400) / 2, 400, 400,
         * 123,
         * // 135);
         * 
         * // g2d.setColor(Color.orange);
         * // g2d.fillArc((size.width - 400) / 2, (size.height - 400) / 2, 400, 400, 45,
         * // 90);
         * 
         * // g2d.setColor(Color.CYAN);
         * // g2d.fillArc((size.width - 400) / 2, (size.height - 400) / 2, 400, 400,
         * 255,
         * // 110);
         */
    }
}
