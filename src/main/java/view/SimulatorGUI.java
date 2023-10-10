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
    private TextField arrivalMean, arrivalVariance, checkInMean, checkInVariance, bagDropMean, bagDropVariance, securityMean, securityVariance, passportMean, passportVariance, ticketInspectionMean, ticketInspectionVariance, time, delay;
    @FXML
    private Button applySettings, exitSettings, defaultSettings, settingsButton, speedUpButton, slowDownButton, startButton;
    @FXML
    private Canvas customerCanvas;
    @FXML
    private Label customerCount, result;

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

        time = (TextField) loader.getNamespace().get("time");
        delay = (TextField) loader.getNamespace().get("delay");
        result = (Label) loader.getNamespace().get("result");
        startButton = (Button) loader.getNamespace().get("startButton");
        speedUpButton = (Button) loader.getNamespace().get("speedUpButton");
        customerCount = (Label) loader.getNamespace().get("customerCount");
        slowDownButton = (Button) loader.getNamespace().get("slowDownButton");
        customerCanvas = (Canvas) loader.getNamespace().get("customerCanvas");
        settingsButton = (Button) loader.getNamespace().get("settingsButton");

        startButton.setOnAction(event -> {
            startButton.setDisable(true);
            settingsButton.setDisable(true);
            controller.startSimulation();
        });

        settingsButton.setOnAction(event -> openSettings());

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

    @Override
    public void openSettings() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Settings.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Settings");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();

            arrivalMean = (TextField) loader.getNamespace().get("arrivalMean");
            arrivalVariance = (TextField) loader.getNamespace().get("arrivalVariance");
            checkInMean = (TextField) loader.getNamespace().get("checkInMean");
            checkInVariance = (TextField) loader.getNamespace().get("checkInVariance");
            bagDropMean = (TextField) loader.getNamespace().get("bagDropMean");
            bagDropVariance = (TextField) loader.getNamespace().get("bagDropVariance");
            securityMean = (TextField) loader.getNamespace().get("securityMean");
            securityVariance = (TextField) loader.getNamespace().get("securityVariance");
            passportMean = (TextField) loader.getNamespace().get("passportMean");
            passportVariance = (TextField) loader.getNamespace().get("passportVariance");
            ticketInspectionMean = (TextField) loader.getNamespace().get("ticketInspectionMean");
            ticketInspectionVariance = (TextField) loader.getNamespace().get("ticketInspectionVariance");
            applySettings = (Button) loader.getNamespace().get("applySettings");
            exitSettings = (Button) loader.getNamespace().get("exitSettings");
            defaultSettings = (Button) loader.getNamespace().get("defaultSettings");

            setDefaultSettings();

            applySettings.setOnAction(e -> {
                applySettings();

                applySettings.setStyle("-fx-background-color: green");
                root.setOnMouseMoved(event -> applySettings.setStyle(""));
            });

            defaultSettings.setOnAction(e -> {
                setDefaultSettings();
                applySettings();

                applySettings.setStyle("");
                applySettings.setStyle("-fx-background-color: green");
                root.setOnMouseMoved(event -> applySettings.setStyle(""));
            });

            exitSettings.setOnAction(e -> {
                stage.close();
                applySettings.setStyle("");
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void setDefaultSettings() {
        arrivalMean.setText("15");
        arrivalVariance.setText("5");

        checkInMean.setText("10");
        checkInVariance.setText("6");

        bagDropMean.setText("10");
        bagDropVariance.setText("10");

        securityMean.setText("15");
        securityVariance.setText("6");

        passportMean.setText("15");
        passportVariance.setText("10");

        ticketInspectionMean.setText("15");
        ticketInspectionVariance.setText("5");
    }

    @Override
    public void applySettings() {
        TextField[] textFields = {
                this.arrivalMean, this.arrivalVariance,
                this.checkInMean, this.checkInVariance,
                this.bagDropMean, this.bagDropVariance,
                this.securityMean, this.securityVariance,
                this.passportMean, this.passportVariance,
                this.ticketInspectionMean, this.ticketInspectionVariance
        };

        int[] values = new int[textFields.length];

        for (int i = 0; i < textFields.length; i++) {
            values[i] = Integer.parseInt(textFields[i].getText());
        }

        controller.setSettings(values);
    }
}
