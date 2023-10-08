package simu.framework;

import controller.IControllerForM;

public abstract class Engine extends Thread implements IEngine {
    private double simulationTime = 0;
    private long delay = 0;
    private final Clock clock;

    protected EventList eventlist;
    protected IControllerForM controller;

    public Engine(IControllerForM controller) {
        this.controller = controller;
        clock = Clock.getInstance();
        eventlist = new EventList();
    }

    public void setSimulationTime(double time) {
        simulationTime = time;
    }

    @Override
    public void setDelay(long delay) {
        this.delay = delay;
    }

    @Override
    public long getDelay() {
        return delay;
    }

    public void run() {
        init();
        while (simulate()) {
            delay();
            clock.setTime(currentTime());
            runBEvents();
            tryCEvents();
        }
        results();
    }

    private void runBEvents() {
        while (eventlist.getNextTime() == clock.getTime()) {
            runEvent(eventlist.remove());
        }
    }

    private double currentTime() {
        return eventlist.getNextTime();
    }

    private boolean simulate() {
        return clock.getTime() < simulationTime;
    }

    private void delay() {
        Trace.out(Trace.Level.INFO, "Delay " + delay);
        try {
            sleep(delay);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    protected abstract void runEvent(Event e);

    protected abstract void tryCEvents();

    protected abstract void init();

    protected abstract void results();

    public abstract int getCustomerCount();
}
