package simu.model;

import simu.framework.*;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;

public class OwnEngine extends Engine {
    private final ArrivalProcess arrivalProcess;
    private final ServicePoint[] servicePoints;

    public OwnEngine() {
        servicePoints = new ServicePoint[3];

        servicePoints[0] = new ServicePoint(new Normal(10, 6), eventlist, EventType.DEP1);
        servicePoints[1] = new ServicePoint(new Normal(10, 10), eventlist, EventType.DEP2);
        servicePoints[2] = new ServicePoint(new Normal(5, 3), eventlist, EventType.DEP3);

        arrivalProcess = new ArrivalProcess(new Negexp(15, 5), eventlist, EventType.ARR1);

    }

    @Override
    protected void runEvent(Event e) {
        Customer customer;
        switch ((EventType) e.getType()) {
            case ARR1:
                customer = new Customer();
                customer.setFlyOutOfEurope(customer.willFlyOutOfEurope());
                servicePoints[0].addToQueue(customer);
                arrivalProcess.generateNext();
                break;
            case DEP1:
                customer = (Customer) servicePoints[0].removeFromQueue();
                servicePoints[1].addToQueue(customer);
                break;
            case DEP2:
                customer = (Customer) servicePoints[1].removeFromQueue();
                servicePoints[2].addToQueue(customer);
                break;
            case DEP3:
                customer = (Customer) servicePoints[2].removeFromQueue();
                customer.setDepartureTime(Clock.getInstance().getClock());
                customer.report();
        }
    }

    @Override
    protected void init() {
        arrivalProcess.generateNext();
    }

    @Override
    protected void tryCEvents() {
        for (ServicePoint servicePoint : servicePoints) {
            if (!servicePoint.isBusy() && servicePoint.hasQueue()) {
                servicePoint.startService();
            }
        }
    }

    @Override
    protected void results() {
        System.out.println("Simulation end at " + Clock.getInstance().getClock());
        System.out.println("Results ... still missing");
    }
}
