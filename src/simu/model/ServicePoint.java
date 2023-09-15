package simu.model;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Clock;
import simu.framework.Event;
import simu.framework.EventList;

import java.util.LinkedList;

public class ServicePoint {
    private final LinkedList<Customer> queue = new LinkedList<>();
    private final ContinuousGenerator generator;
    private final EventList eventList;
    private final EventType scheduledEventType;

    private boolean reserved = false;

    public ServicePoint(ContinuousGenerator generator, EventList eventList, EventType type) {
        this.eventList = eventList;
        this.generator = generator;
        this.scheduledEventType = type;
    }

    public void addToQueue(Customer c) {
        queue.add(c);
    }

    public Customer removeFromQueue() {
        reserved = false;
        return queue.poll();
    }

    public void startService() {
        reserved = true;
        double serviceTime = generator.sample();
        eventList.add(new Event(scheduledEventType, Clock.getInstance().getClock() + serviceTime));
    }

    public boolean isBusy() {
        return reserved;
    }

    public boolean hasQueue() {
        return !queue.isEmpty();
    }
}
