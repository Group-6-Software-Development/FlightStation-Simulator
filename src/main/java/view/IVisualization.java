package view;

/**
 * This interface is responsible for the visualization of the simulation.
 */
public interface IVisualization {
    /**
     * Clears the canvas.
     */
    void clearScreen();

    /**
     * Draws a red circle representing a new customer that has entered the system.
     */
    void newCustomer();

    /**
     * Draws a green circle representing a customer that has left the system.
     */
    void customerLeaves();
}
