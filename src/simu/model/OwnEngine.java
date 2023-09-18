package simu.model;

import simu.framework.*;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;

public class OwnEngine extends Engine {
    private final ArrivalProcess arrivalProcess;
    private final ServicePoint[] servicePoints;

    public OwnEngine() {
        servicePoints = new ServicePoint[12];
        // Uudet Eventit tehdään tässä
        servicePoints[0] = new ServicePoint(new Normal(10, 6), eventlist, EventType.DEP1);
        servicePoints[1] = new ServicePoint(new Normal(10, 10), eventlist, EventType.DEP2);
        servicePoints[2] = new ServicePoint(new Normal(15, 6), eventlist, EventType.DEP3);
        servicePoints[3] = new ServicePoint(new Normal(15, 3), eventlist, EventType.DEP4);
        servicePoints[4] = new ServicePoint(new Normal(15, 10), eventlist, EventType.DEP5);
        servicePoints[5] = new ServicePoint(new Normal(15, 5), eventlist, EventType.DEP6);
        servicePoints[6] = new ServicePoint(new Normal(5, 3), eventlist, EventType.DEP7);
        servicePoints[7] = new ServicePoint(new Normal(5, 2), eventlist, EventType.DEP8);
        servicePoints[8] = new ServicePoint(new Normal(25, 13), eventlist, EventType.DEP9);
        servicePoints[9] = new ServicePoint(new Normal(35, 20), eventlist, EventType.DEP10);
        servicePoints[10] = new ServicePoint(new Normal(5, 3), eventlist, EventType.DEP11);
        servicePoints[11] = new ServicePoint(new Normal(15, 3), eventlist, EventType.DEP12);
        arrivalProcess = new ArrivalProcess(new Negexp(15, 5), eventlist, EventType.ARR1);

    }

    @Override
    protected void runEvent(Event e) {
        Customer customer;
        switch ((EventType) e.getType()) {
            case ARR1:
                servicePoints[0].addToQueue(new Customer());
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
                customer = (Customer) servicePoints[0].removeFromQueue();
                servicePoints[3].addToQueue(customer);
                break;
            case DEP4:
                customer = (Customer) servicePoints[1].removeFromQueue();
                servicePoints[4].addToQueue(customer);
                break;
            case DEP5:
                customer = (Customer) servicePoints[0].removeFromQueue();
                servicePoints[5].addToQueue(customer);
                break;
            case DEP6:
                customer = (Customer) servicePoints[1].removeFromQueue();
                servicePoints[6].addToQueue(customer);
                break;
            case DEP7:
                customer = (Customer) servicePoints[0].removeFromQueue();
                servicePoints[7].addToQueue(customer);
                break;
            case DEP8:
                customer = (Customer) servicePoints[1].removeFromQueue();
                servicePoints[8].addToQueue(customer);
                break;
            case DEP9:
                customer = (Customer) servicePoints[0].removeFromQueue();
                servicePoints[9].addToQueue(customer);
                break;
            case DEP10:
                customer = (Customer) servicePoints[1].removeFromQueue();
                servicePoints[10].addToQueue(customer);
                break;
            case DEP11:
                customer = (Customer) servicePoints[0].removeFromQueue();
                servicePoints[11].addToQueue(customer);
                break;
            case DEP12:
                customer = (Customer) servicePoints[12].removeFromQueue();
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
