package controller;

import dao.VariablesDao;
import javafx.application.Platform;
import simu.entity.Variables;
import simu.framework.IEngine;
import simu.model.OwnEngine;
import view.ISimulatorUI;

import java.util.List;

public class Controller implements IControllerForM, IControllerForV{
    private final IEngine engine = new OwnEngine(this);
    private final VariablesDao variablesDao = new VariablesDao();
    private final ISimulatorUI ui;

    public Controller(ISimulatorUI ui) {
        this.ui = ui;
    }

    @Override
    public void startSimulation() {
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

    @Override
    public void setSettings(int[] values) {
        engine.setSettings(values);
    }

    @Override
    public List<Variables> getResults() {
        return variablesDao.getAllVariables();
    }
}
