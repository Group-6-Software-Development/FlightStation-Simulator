package simu.model;

import controller.IControllerForM;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.entity.VariablesCalculation;
import simu.framework.ArrivalProcess;
import simu.framework.Clock;
import simu.framework.Engine;
import simu.framework.Event;

public class OwnEngine extends Engine {
    private final ArrivalProcess arrivalProcess;
    private final ServicePoint[] servicePoints;
    private int C = 0;      //Counter for customer amount

    public OwnEngine(IControllerForM controller) {

        super(controller);

        // mean service time and standard deviation from that service time +-
        servicePoints = new ServicePoint[5];
        servicePoints[0] = new ServicePoint(new Normal(10, 6), eventlist, EventType.CHECKIN);
        servicePoints[1] = new ServicePoint(new Normal(10, 10), eventlist, EventType.BAGDROP);
        servicePoints[2] = new ServicePoint(new Normal(15, 6), eventlist, EventType.SECURITYCHECK);
        servicePoints[3] = new ServicePoint(new Normal(15, 10), eventlist, EventType.PASSPORTCHECK);
        servicePoints[4] = new ServicePoint(new Normal(15, 5), eventlist, EventType.TICKETINSPECTION);

        arrivalProcess = new ArrivalProcess(new Negexp(15, 5), eventlist, EventType.ARR1);
    }

    @Override
    protected void runEvent(Event e) {
        Customer customer;
        switch ((EventType) e.getType()) {
            case ARR1:
                customer = new Customer();
                servicePoints[0].addToQueue(customer);
                arrivalProcess.generateNext();
                controller.visualizeCustomer();
                break;
            case CHECKIN:
                customer = servicePoints[0].takeFromQueue();
                customer.setRiEnd(Clock.getInstance().getTime());
                VariablesCalculation.servicePointRi(customer.getRiStart(), customer.getRiEnd(), EventType.CHECKIN);
                VariablesCalculation.servicePointC(EventType.CHECKIN);
                servicePoints[1].addToQueue(customer);
                break;
            case BAGDROP:
                VariablesCalculation.servicePointC(EventType.BAGDROP);
                customer = servicePoints[1].takeFromQueue();
                customer.setRiEnd(Clock.getInstance().getTime());
                VariablesCalculation.servicePointRi(customer.getRiStart(), customer.getRiEnd(), EventType.BAGDROP);
                servicePoints[2].addToQueue(customer);
                break;
            case SECURITYCHECK:
                VariablesCalculation.servicePointC(EventType.SECURITYCHECK);
                customer = servicePoints[2].takeFromQueue();
                customer.setRiEnd(Clock.getInstance().getTime());
                VariablesCalculation.servicePointRi(customer.getRiStart(), customer.getRiEnd(), EventType.SECURITYCHECK);
                servicePoints[3].addToQueue(customer);
                break;
            case PASSPORTCHECK:
                VariablesCalculation.servicePointC(EventType.PASSPORTCHECK);
                customer = servicePoints[3].takeFromQueue();
                customer.setRiEnd(Clock.getInstance().getTime());
                VariablesCalculation.servicePointRi(customer.getRiStart(), customer.getRiEnd(), EventType.PASSPORTCHECK);
                servicePoints[4].addToQueue(customer);
                break;
            case TICKETINSPECTION:
                VariablesCalculation.servicePointC(EventType.TICKETINSPECTION);
                customer = servicePoints[4].takeFromQueue();
                customer.setRiEnd(Clock.getInstance().getTime());
                VariablesCalculation.servicePointRi(customer.getRiEnd(), customer.getRiStart(), EventType.TICKETINSPECTION);
                customer.setDepartureTime(Clock.getInstance().getTime());
                customer.report();
                C++;        //add +1 counter for customer
                break;
        }
        VariablesCalculation.setSimulationTotalTime(Clock.getInstance().getTime()); // set the total simulation time
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
        System.out.println("Simulation end at " + Clock.getInstance().getTime());
        System.out.println("Results ... still missing");
        System.out.println("Total amount of customers that passed into the plane: " + C);
        controller.showEndTime(Clock.getInstance().getTime());
    }

    @Override
    public int getCustomerCount() {
        return C;
    }
}
