package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Visualization implements IVisualization {
    private final GraphicsContext gc;
    private final Canvas canvas;

    double i = 0;
    double j = 0;

    double x = 0;
    double y = 0;

    public Visualization(Canvas canvas) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        clearScreen();
    }

    public void clearScreen() {
        gc.setFill(Color.YELLOW);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void newCustomer() {
        gc.setFill(Color.RED);
        gc.fillOval(i, j, 10, 10);

        i = (i + 10) % canvas.getWidth();
        if (i == 0) j += 10;
    }

    public void customerLeaves() {
        gc.setFill(Color.GREEN);
        gc.fillOval(x, y, 10, 10);

        x = (x + 10) % canvas.getWidth();
        if (x == 0) y += 10;
    }
}
