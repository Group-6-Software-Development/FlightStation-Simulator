package view;

public interface ISimulatorUI {
    double getTime();

    long getDelay();

    void setEndTime(double time);

    IVisualization getVisualization();
}
