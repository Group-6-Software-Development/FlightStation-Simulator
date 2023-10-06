package simu.framework;

public interface IEngine {
    void setSimulationTime(double time);
    void setDelay(long delay);
    long getDelay();

    int getCustomerCount();
}
