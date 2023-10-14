package controller;

public interface IControllerForM {
    /**
     * Shows the end time of the simulation.
     *
     * @param time The end time of the simulation.
     */
    void showEndTime(double time);

    /**
     * Visualizes a new customer arriving.
     */
    void visualizeCustomer();

    /**
     * Visualizes a customer leaving.
     */
    void visualizeCustomerLeaves();
}
