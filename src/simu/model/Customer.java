package simu.model;

import simu.framework.Clock;
import simu.framework.Trace;

public class Customer {
    private double arrivalTime;
    private double departureTime;
    private final int id;
    private static int i = 1;
    private static long sum = 0;

    private boolean flyOutOfEurope;

    public Customer() {
        id = i++;

        arrivalTime = Clock.getInstance().getClock();
        Trace.out(Trace.Level.INFO, "New Customer nbr " + id + " arrived at " + arrivalTime);
    }

    public double getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(double departureTime) {
        this.departureTime = departureTime;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getId() {
        return id;
    }


    public boolean willFlyOutOfEurope() {   //Feature to generate a random boolean with 10% chance of being true.
        return Math.random() <= 0.9;        //Math.random generates 0 / 1 and compares it to 0.1, which makes it 10%.
    }

    public void setFlyOutOfEurope(boolean flyOutOfEurope) {
        this.flyOutOfEurope = flyOutOfEurope;
    }

    public void report() {
        Trace.out(Trace.Level.INFO, "\nCustomer " + id + " ready! ");
        Trace.out(Trace.Level.INFO, "Customer " + id + " arrived: " + arrivalTime);
        Trace.out(Trace.Level.INFO, "Customer " + id + " departed: " + departureTime);
        Trace.out(Trace.Level.INFO, "Customer " + id + " stayed: " + (departureTime - arrivalTime));
        sum += (departureTime - arrivalTime);
        double average = sum / id;
        System.out.println("Average time of customers' stay so far " + average);
    }
}
