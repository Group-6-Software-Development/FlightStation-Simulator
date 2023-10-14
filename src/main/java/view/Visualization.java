package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class is responsible for the visualization of the simulation.
 */
public class Visualization implements IVisualization {
    /**
     * The GraphicsContext object used to draw on the canvas.
     */
    private final GraphicsContext gc;
    /**
     * The canvas on which the visualization will be drawn.
     */
    private final Canvas canvas;

    /**
     * The coordinates of the next new customer.
     */
    double i = 0;
    /**
     * The coordinates of the next customer that leaves the system.
     */
    double j = 0;

    /**
     * The coordinates of the next customer that leaves the system.
     */
    double x = 0;
    /**
     * The coordinates of the next customer that leaves the system.
     */
    double y = 0;

    /**
     * Constructor for the Visualization class.
     *
     * @param canvas The canvas on which the visualization will be drawn.
     */
    public Visualization(Canvas canvas) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        clearScreen();
    }

    /**
     * Clears the canvas.
     */
    public void clearScreen() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    /**
     * Draws a red circle representing a new customer that has entered the system.
     */
    public void newCustomer() {
        gc.setFill(Color.RED);
        gc.fillOval(i, j, 10, 10);

        i = (i + 10) % canvas.getWidth();
        if (i == 0) j += 10;
    }

    /**
     * Draws a green circle representing a customer that has left the system.
     */
    public void customerLeaves() {
        gc.setFill(Color.GREEN);
        gc.fillOval(x, y, 10, 10);

        x = (x + 10) % canvas.getWidth();
        if (x == 0) y += 10;
    }
}
