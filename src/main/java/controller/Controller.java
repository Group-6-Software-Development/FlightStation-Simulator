package controller;

import dao.VariablesDao;
import javafx.application.Platform;
import simu.entity.Variables;
import simu.framework.IEngine;
import simu.model.OwnEngine;
import view.ISimulatorUI;

import java.util.List;

/**
 * Controller class for the MVC pattern.
 */
public class Controller implements IControllerForM, IControllerForV{
    private final IEngine engine = new OwnEngine(this);
    private final VariablesDao variablesDao = new VariablesDao();
    private final ISimulatorUI ui;

    /**
     * Constructor for the controller.
     *
     * @param ui The UI to be used.
     */
    public Controller(ISimulatorUI ui) {
        this.ui = ui;
    }

    /**
     * Starts the simulation.
     */
    @Override
    public void startSimulation() {
        engine.setSimulationTime(ui.getTime());
        engine.setDelay(ui.getDelay());
        ui.getVisualization().clearScreen();
        ((Thread) engine).start();
    }

    /**
     * Slow down the simulation.
     */
    @Override
    public void slowDown() {
        engine.setDelay((long) (engine.getDelay() * 1.10));
    }

    /**
     * Speed up the simulation.
     */
    @Override
    public void speedUp() {
        engine.setDelay((long) (engine.getDelay() * 0.9));
    }

    /**
     * Shows the end time of the simulation.
     * @param time The end time of the simulation.
     */
    @Override
    public void showEndTime(double time) {
        Platform.runLater(() -> ui.setEndTime(time));
    }

    /**
     * Visualizes a new customer arriving.
     */
    @Override
    public void visualizeCustomer() {
        Platform.runLater(() -> ui.getVisualization().newCustomer());
    }

    /**
     * Visualizes a customer leaving.
     */
    @Override
    public void visualizeCustomerLeaves() {
        Platform.runLater(() -> ui.getVisualization().customerLeaves());
    }

    /**
     * Returns the number of customers that were served.
     * @return The number of customers that were served.
     */
    @Override
    public int getCustomerCount() {
        return engine.getCustomerCount();
    }

    /**
     * Set the settings for the simulation.
     * @param values The settings for the simulation.
     */
    @Override
    public void setSettings(int[] values) {
        engine.setSettings(values);
    }

    /**
     * Returns the results of the simulation.
     * @return The results of the simulation.
     */
    @Override
    public List<Variables> getResults() {
        return variablesDao.getAllVariables();
    }
}
