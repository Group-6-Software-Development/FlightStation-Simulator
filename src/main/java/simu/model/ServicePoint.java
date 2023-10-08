package simu.model;

import eduni.distributions.ContinuousGenerator;
import simu.entity.VariablesCalculation;
import simu.framework.Clock;
import simu.framework.Event;
import simu.framework.EventList;

import java.util.LinkedList;

public class ServicePoint {
    private final LinkedList<Customer> queue = new LinkedList<>();
    private final ContinuousGenerator generator;
    private final EventList eventList;
    private final EventType scheduledEventType;
    private Customer customer;

    private boolean reserved = false;

    public ServicePoint(ContinuousGenerator generator, EventList eventList, EventType type) {
        this.eventList = eventList;
        this.generator = generator;
        this.scheduledEventType = type;
    }

    public void addToQueue(Customer c) {
        customer.setRiStart(Clock.getInstance().getTime());
        queue.add(c);
    }

    public Customer takeFromQueue() {
        setReserved(false);
        return queue.poll();
    }

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


    public boolean isBusy() {
        return reserved;
    }

    public boolean hasQueue() {
        return !queue.isEmpty();
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
