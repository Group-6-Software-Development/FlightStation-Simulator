package simu.framework;

public abstract class Engine {
    private double simulationTime = 0;
    private final Clock clock;

    protected EventList eventlist;

    public Engine() {
        clock = Clock.getInstance();
        eventlist = new EventList();
    }

    public void setSimulationTime(double time) {
        simulationTime = time;
    }

    public void run() {
        init();
        while (simulate()) {
            clock.setTime(currentTime());
            runBEvents();
            tryCEvents();
        }
        results();
    }

    private void runBEvents() {
        while (eventlist.getNextTime() == clock.getClock()) {
            runEvent(eventlist.remove());
        }
    }

    private double currentTime() {
        return eventlist.getNextTime();
    }

    private boolean simulate() {
        return clock.getClock() < simulationTime;
    }

    protected abstract void runEvent(Event e);

    protected abstract void tryCEvents();

    protected abstract void init();

    protected abstract void results();
}
