package simu.model;

import simu.framework.Clock;
import simu.framework.Trace;

/**
 * Customer class represents a customer in the simulation.
 */
public class Customer {
    private final double arrivalTime;
    private double departureTime;
    private final int id;
    private static int nextAvailableId = 1;
    private static double totalCustomerStayTime = 0.0;

    private double riStart, riEnd; // response time (from arrival to queue to exit from service point)

    /**
     * Constructor for Customer class
     */
    public Customer() {
        this.id = nextAvailableId++;
        this.arrivalTime = Clock.getInstance().getTime();
        Trace.out(Trace.Level.INFO, "New Customer nbr " + id + " arrived at " + arrivalTime);
    }

    /**
     * Getter for arrival time
     *
     * @param departureTime the departure time
     */
    public void setDepartureTime(double departureTime) {
        this.departureTime = departureTime;
        totalCustomerStayTime += (calculateStayTime());
    }

    public double getRiStart() {
        return riStart;
    }

    public void setRiStart(double riStart) {
        this.riStart = riStart;
    }

    public double getRiEnd() {
        return riEnd;
    }

    public void setRiEnd(double riEnd) {
        this.riEnd = riEnd;
    }

    /**
     * Report the customer's arrival and departure time
     * and the time the customer stayed in the system
     * and the average time of customers' stay so far
     */
    public void report() {
        Trace.out(Trace.Level.INFO, "\nCustomer " + id + " ready! ");
        Trace.out(Trace.Level.INFO, "Customer " + id + " arrived: " + arrivalTime);
        Trace.out(Trace.Level.INFO, "Customer " + id + " departed: " + departureTime);
        Trace.out(Trace.Level.INFO, "Customer " + id + " stayed: " + (calculateStayTime()));
        System.out.println("Average time of customers' stay so far " + calculateAverageStayTime());
    }

    /**
     * Calculate the time the customer stayed in the system
     * @return the time the customer stayed in the system
     */
    public double calculateStayTime() {
        return departureTime - arrivalTime;
    }

    /**
     * Calculate the average time of customers' stay so far
     * @return the average time of customers' stay so far
     */
    public double calculateAverageStayTime() {
        return totalCustomerStayTime / id;
    }

    /**
     * Returns the customer's variables as a string
     * @return the customer's variables as a string
     */
    @Override
    public String toString() {
        return "Customer{" +
                "arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", id=" + id +
                '}';
    }
}