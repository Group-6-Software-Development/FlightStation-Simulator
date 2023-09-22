package view;

import controller.Controller;
import controller.IControllerForV;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import simu.framework.Trace;
import simu.framework.Trace.Level;

import java.text.DecimalFormat;

public class SimulatorGUI extends Application implements ISimulatorUI {
    private IControllerForV controller;

    private TextField time;
    private TextField delay;
    private Label result;

    private Button startButton;

    private IVisualization display;

    @Override
    public void init() {
        Trace.setTraceLevel(Level.INFO);
        controller = new Controller(this);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setOnCloseRequest(t -> {
                Platform.exit();
                System.exit(0);
            });

            primaryStage.setTitle("Simulator");

            startButton = new Button();
            startButton.setText("Start");
            startButton.setOnAction(event -> {
                controller.startSimulation();
                startButton.setDisable(true);
            });

            Button slowDownButton = new Button();
            slowDownButton.setText("Slow down");
            slowDownButton.setOnAction(e -> controller.slowDown());

            Button speedUpButton = new Button();
            speedUpButton.setText("Speed up");
            speedUpButton.setOnAction(e -> controller.speedUp());

            Label timeLabel = new Label("Simulation time:");
            timeLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            time = new TextField("Enter time");
            time.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            time.setPrefWidth(150);

            Label delayLabel = new Label("Delay:");
            delayLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            delay = new TextField("Enter delay");
            delay.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            delay.setPrefWidth(150);

            Label resultLabel = new Label("Total time:");
            resultLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            result = new Label();
            result.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            result.setPrefWidth(150);

            HBox hBox = new HBox();
            hBox.setPadding(new Insets(15, 12, 15, 12));
            hBox.setSpacing(10);

            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setVgap(10);
            grid.setHgap(5);

            grid.add(timeLabel, 0, 0);
            grid.add(time, 1, 0);
            grid.add(delayLabel, 0, 1);
            grid.add(delay, 1, 1);
            grid.add(resultLabel, 0, 2);
            grid.add(result, 1, 2);
            grid.add(startButton, 0, 3);
            grid.add(speedUpButton, 0, 4);
            grid.add(slowDownButton, 1, 4);

            display = new Visualization(400, 200);

            hBox.getChildren().addAll(grid, (Canvas) display);

            Scene scene = new Scene(hBox);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
    }

    @Override
    public IVisualization getVisualization() {
        return display;
    }
}
