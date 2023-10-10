package view;

/**
 * This interface is used to define the methods that are used in SimulatorGUI
 */
public interface ISimulatorUI {
    /**
     * @return the time from the GUI.
     */
    double getTime();

    /**
     * @return the delay from the GUI.
     */
    long getDelay();

    /**
     * This method is used to show the end time and the total customer count in the GUI once the simulation is finished.
     * @param time the end time of the simulation.
     */
    void setEndTime(double time);

    /**
     * @return the display.
     */
    IVisualization getVisualization();

    /**
     * This method is used to open the settings GUI.
     */
    void openSettings();

    /**
     * This method is used to set the default settings in the settings GUI.
     */
    void setDefaultSettings();

    /**
     * This method is used to apply the settings in the settings GUI.
     */
    void applySettings();

    /**
     * This method is used to open the results GUI.
     */
    void openResults();
}
