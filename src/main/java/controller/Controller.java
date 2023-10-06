package controller;

import javafx.application.Platform;
import simu.framework.IEngine;
import simu.model.OwnEngine;
import view.ISimulatorUI;

public class Controller implements IControllerForM, IControllerForV{

    private IEngine engine;
    private final ISimulatorUI ui;

    public Controller(ISimulatorUI ui) {
        this.ui = ui;
    }

    @Override
    public void startSimulation() {
        engine = new OwnEngine(this);
        engine.setSimulationTime(ui.getTime());
        engine.setDelay(ui.getDelay());
        ui.getVisualization().clearScreen();
        ((Thread) engine).start();
    }

    @Override
    public void slowDown() {
        engine.setDelay((long) (engine.getDelay() * 1.10));
    }

    @Override
    public void speedUp() {
        engine.setDelay((long) (engine.getDelay() * 0.9));
    }

    @Override
    public void showEndTime(double time) {
        Platform.runLater(() -> ui.setEndTime(time));
    }

    @Override
    public void visualizeCustomer() {
        Platform.runLater(() -> ui.getVisualization().newCustomer());
    }

    @Override
    public void visualizeCustomerLeaves() {
        Platform.runLater(() -> ui.getVisualization().customerLeaves());
    }

    @Override
    public int getCustomerCount() {
        return engine.getCustomerCount();
    }
}
