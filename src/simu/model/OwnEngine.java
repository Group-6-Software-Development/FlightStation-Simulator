package simu.model;

import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.ArrivalProcess;
import simu.framework.Clock;
import simu.framework.Engine;
import simu.framework.Event;

public class OwnEngine extends Engine {
    private final ArrivalProcess arrivalProcess;
    private final ServicePoint[] servicePoints;

    public OwnEngine() {
        servicePoints = new ServicePoint[12];
        // Uudet Eventit tehdään tässä
        servicePoints[0] = new ServicePoint(new Normal(10, 6), eventlist, EventType.CHECKIN);
        servicePoints[1] = new ServicePoint(new Normal(10, 10), eventlist, EventType.BAGDROP);
        servicePoints[2] = new ServicePoint(new Normal(15, 6), eventlist, EventType.SECURITYCHECK);
        servicePoints[3] = new ServicePoint(new Normal(15, 3), eventlist, EventType.RANDOMINSPECTION);
        servicePoints[4] = new ServicePoint(new Normal(15, 10), eventlist, EventType.PASSPORTCHECK);
        servicePoints[5] = new ServicePoint(new Normal(15, 5), eventlist, EventType.TICKETINSPECTION);
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
            case CHECKIN:
                customer = (Customer) servicePoints[0].removeFromQueue();
                if ((customer.isOnlineCheckIn() = true)) {
                    break;
                } else {
                    servicePoints[1].addToQueue(customer);
                }
                break;
            case BAGDROP:
                customer = (Customer) servicePoints[1].removeFromQueue();
                servicePoints[2].addToQueue(customer);
                break;
            case SECURITYCHECK:
                customer = (Customer) servicePoints[0].removeFromQueue();
                servicePoints[3].addToQueue(customer);
                break;
            case RANDOMINSPECTION:
                customer = (Customer) servicePoints[1].removeFromQueue();
                servicePoints[4].addToQueue(customer);
                break;
            case PASSPORTCHECK:
                customer = (Customer) servicePoints[0].removeFromQueue();
                if ((customer.setFlyOutOfEurope() = true)) {
                    servicePoints[5].addToQueue(customer);
                } else {
                    break;
                }
                break;
            case TICKETINSPECTION:
                customer = (Customer) servicePoints[1].removeFromQueue();
                customer.setDepartureTime(Clock.getInstance().getClock());
                customer.report();
                break;
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
