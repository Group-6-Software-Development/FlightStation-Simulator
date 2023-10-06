package view;

import controller.Controller;
import controller.IControllerForV;
import javafx.application.Application;
import javafx.application.Platform;
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
    private Canvas customerCanvas;
    @FXML
    private Button speedUpButton;
    @FXML
    private Button slowDownButton;
    @FXML
    private Label customerCount;
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
        time = (TextField) loader.getNamespace().get("time");
        delay = (TextField) loader.getNamespace().get("delay");
        result = (Label) loader.getNamespace().get("result");
        customerCanvas = (Canvas) loader.getNamespace().get("customerCanvas");

        startButton.setOnAction(event -> {
            controller.startSimulation();
            startButton.setDisable(true);
        });

        speedUpButton.setOnAction(e -> controller.speedUp());
        slowDownButton.setOnAction(e -> controller.slowDown());

        display = new Visualization(customerCanvas);

        stage.setTitle("Simulator");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();

        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
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
