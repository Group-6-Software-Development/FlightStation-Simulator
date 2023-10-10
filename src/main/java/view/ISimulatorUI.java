package view;

public interface ISimulatorUI {
    double getTime();

    long getDelay();

    void setEndTime(double time);

    IVisualization getVisualization();

    void openSettings();

    void setDefaultSettings();

    void applySettings();

    void openResults();
}
