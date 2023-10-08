package simu.model;

import controller.IControllerForM;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.ArrivalProcess;
import simu.framework.Clock;
import simu.framework.Engine;
import simu.framework.Event;

public class OwnEngine extends Engine {
    private ArrivalProcess arrivalProcess;
    private ServicePoint[] servicePoints;

    private int arrivalMean = 15;
    private int arrivalVariance = 5;
    private int checkInMean = 10;
    private int checkInVariance = 6;
    private int bagDropMean = 10;
    private int bagDropVariance = 10;
    private int securityMean = 15;
    private int securityVariance = 6;
    private int passportMean = 15;
    private int passportVariance = 10;
    private int ticketInspectionMean = 15;
    private int ticketInspectionVariance = 5;

    private int customerCount = 0;

    public OwnEngine(IControllerForM controller) {
        super(controller);
        setupServicePoints();
    }

    @Override
    public void setupServicePoints() {
        int[] means = {
                checkInMean, bagDropMean, securityMean, passportMean, ticketInspectionMean
        };

        int[] variances = {
                checkInVariance, bagDropVariance, securityVariance, passportVariance, ticketInspectionVariance
        };

        EventType[] eventTypes = {
                EventType.CHECKIN, EventType.BAGDROP, EventType.SECURITYCHECK, EventType.PASSPORTCHECK, EventType.TICKETINSPECTION
        };

        servicePoints = new ServicePoint[5];

        for (int i = 0; i < 5; i++) {
            servicePoints[i] = new ServicePoint(new Normal(means[i], variances[i]), eventlist, eventTypes[i]);
        }

        arrivalProcess = new ArrivalProcess(new Negexp(arrivalMean, arrivalVariance), eventlist, EventType.ARR1);

        System.out.println("Settings applied");
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
                customer = servicePoints[0].removeFromQueue();
                servicePoints[1].addToQueue(customer);
                break;
            case BAGDROP:
                customer = servicePoints[1].removeFromQueue();
                servicePoints[2].addToQueue(customer);
                break;
            case SECURITYCHECK:
                customer = servicePoints[2].removeFromQueue();
                servicePoints[3].addToQueue(customer);
                break;
            case PASSPORTCHECK:
                customer = servicePoints[3].removeFromQueue();
                servicePoints[4].addToQueue(customer);
                break;
            case TICKETINSPECTION:
                customer = servicePoints[4].removeFromQueue();
                customer.setDepartureTime(Clock.getInstance().getClock());
                customer.report();
                controller.visualizeCustomerLeaves();
                customerCount++;
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
        System.out.println("Total amount of customers that passed into the plane: " + customerCount);

        for (ServicePoint servicePoint : servicePoints) {
            System.out.printf("Utilization of %s is %.2f%%\n", servicePoint, servicePoint.getUtilization());
        }
        controller.showEndTime(Clock.getInstance().getClock());
    }

    @Override
    public int getCustomerCount() {
        return customerCount;
    }

    @Override
    public void setSettings(int[] values) {
        if (values.length != 12) {
            throw new IllegalArgumentException("Expected 12 values for settings.");
        }

        this.arrivalMean = values[0];
        this.arrivalVariance = values[1];
        this.checkInMean = values[2];
        this.checkInVariance = values[3];
        this.bagDropMean = values[4];
        this.bagDropVariance = values[5];
        this.securityMean = values[6];
        this.securityVariance = values[7];
        this.passportMean = values[8];
        this.passportVariance = values[9];
        this.ticketInspectionMean = values[10];
        this.ticketInspectionVariance = values[11];

        for (int value : values) {
            System.out.println(value);
        }

        setupServicePoints();
    }
}
