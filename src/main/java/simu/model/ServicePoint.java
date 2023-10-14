package simu.model;

import eduni.distributions.ContinuousGenerator;
import simu.entity.VariablesCalculation;
import simu.framework.Clock;
import simu.framework.Event;
import simu.framework.EventList;

import java.util.LinkedList;

/**
 * ServicePoint class
 */
public class ServicePoint {
    private final LinkedList<Customer> queue = new LinkedList<>();
    private final ContinuousGenerator generator;
    private final EventList eventList;
    private final EventType scheduledEventType;

    private boolean reserved = false;

    /**
     * Constructor for ServicePoint class
     *
     * @param generator service time generator
     * @param eventList event list
     * @param type      event type
     */
    public ServicePoint(ContinuousGenerator generator, EventList eventList, EventType type) {
        this.eventList = eventList;
        this.generator = generator;
        this.scheduledEventType = type;
    }

    /**
     * Add customer to queue
     * @param c customer
     */
    public void addToQueue(Customer c) {
        c.setRiEnd(Clock.getInstance().getTime());
        queue.add(c);
    }

    /**
     * Take customer from queue
     * @return customer from queue
     */
    public Customer takeFromQueue() {
        setReserved(false);
        return queue.poll();
    }

    /**
     * Start service
     */
    public void startService() {
        setReserved(true);
        double serviceTime = generator.sample();
        if (EventType.CHECKIN.equals(scheduledEventType)) {
            VariablesCalculation.servicePointB(serviceTime, EventType.CHECKIN);
        } else if (EventType.BAGDROP.equals(scheduledEventType)) {
            VariablesCalculation.servicePointB(serviceTime, EventType.BAGDROP);
        } else if (EventType.SECURITYCHECK.equals(scheduledEventType)) {
            VariablesCalculation.servicePointB(serviceTime, EventType.SECURITYCHECK);
        } else if (EventType.PASSPORTCHECK.equals(scheduledEventType)) {
            VariablesCalculation.servicePointB(serviceTime, EventType.PASSPORTCHECK);
        } else if (EventType.TICKETINSPECTION.equals(scheduledEventType)) {
            VariablesCalculation.servicePointB(serviceTime, EventType.TICKETINSPECTION);
        }
        eventList.add(new Event(scheduledEventType, Clock.getInstance().getTime() + serviceTime));
    }

    /**
     * Check if service point is busy
     * @return true if service point is busy
     */
    public boolean isBusy() {
        return reserved;
    }

    /**
     * Check if queue is empty
     * @return true if queue is empty
     */
    public boolean hasQueue() {
        return !queue.isEmpty();
    }

    /**
     * Set reserved status
     * @param reserved reserved status
     */
    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
