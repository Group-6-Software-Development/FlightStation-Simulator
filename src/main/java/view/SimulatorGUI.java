package view;

import controller.Controller;
import controller.IControllerForV;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import simu.framework.Trace;
import simu.framework.Trace.Level;

import java.text.DecimalFormat;

public class SimulatorGUI extends Application implements ISimulatorUI {
    @FXML
    private Button speedUpButton;
    @FXML
    private Button slowDownButton;
    @FXML
    private Label customerCount;
    @FXML
    private Canvas customerDisplay;
    @FXML
    private TextField time;
    @FXML
    private TextField delay;
    @FXML
    private Label result;
    @FXML
    private Button startButton;

    private IControllerForV controller;

    private IVisualization display;

    @Override
    public void init() {
        Trace.setTraceLevel(Level.INFO);
        controller = new Controller(this);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Simulator.fxml"));
        Parent root = loader.load();

        startButton = (Button) loader.getNamespace().get("startButton");
        speedUpButton = (Button) loader.getNamespace().get("speedUpButton");
        slowDownButton = (Button) loader.getNamespace().get("slowDownButton");
        customerCount = (Label) loader.getNamespace().get("customerCount");
        customerDisplay = (Canvas) loader.getNamespace().get("customerDisplay");
        time = (TextField) loader.getNamespace().get("time");
        delay = (TextField) loader.getNamespace().get("delay");
        result = (Label) loader.getNamespace().get("result");

        startButton.setOnAction(event -> {
            controller.startSimulation();
            startButton.setDisable(true);
        });

        slowDownButton.setOnAction(e -> controller.slowDown());

        speedUpButton.setOnAction(e -> controller.speedUp());

        display = new Visualization((int) customerDisplay.getWidth(), (int) customerDisplay.getHeight());

        stage.setTitle("Simulator");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public double getTime() {
        return Double.parseDouble(time.getText());
    }

    @Override
    public long getDelay() {
        return Long.parseLong(delay.getText());
    }

    @Override
    public void setEndTime(double time) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        this.result.setText(decimalFormat.format(time));
        int totalCustomerCount = controller.getCustomerCount();
        customerCount.setText(String.valueOf(totalCustomerCount));
    }

    @Override
    public IVisualization getVisualization() {
        return display;
    }
}
