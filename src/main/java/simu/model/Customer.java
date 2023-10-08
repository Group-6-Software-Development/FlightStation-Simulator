package simu.model;

import simu.framework.Clock;
import simu.framework.Trace;

public class Customer {
    private double arrivalTime;
    private double departureTime;
    private final int id;
    private static int nextAvailableId = 1;
    private static double totalCustomerStayTime = 0.0;

    private double riStart, riEnd; // response time (from arrival to queue to exit from service point)


    public Customer() {
        this.id = nextAvailableId++;
        this.arrivalTime = Clock.getInstance().getTime();
        Trace.out(Trace.Level.INFO, "New Customer nbr " + id + " arrived at " + arrivalTime);
    }

    public double getDepartureTime() {
        return departureTime;
    }

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

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getId() {
        return id;
    }


    public void report() {
        Trace.out(Trace.Level.INFO, "\nCustomer " + id + " ready! ");
        Trace.out(Trace.Level.INFO, "Customer " + id + " arrived: " + arrivalTime);
        Trace.out(Trace.Level.INFO, "Customer " + id + " departed: " + departureTime);
        Trace.out(Trace.Level.INFO, "Customer " + id + " stayed: " + (calculateStayTime()));
        System.out.println("Average time of customers' stay so far " + calculateAverageStayTime());
    }

    public double calculateStayTime() {
        return departureTime - arrivalTime;
    }

    public double calculateAverageStayTime() {
        return totalCustomerStayTime / id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", id=" + id +
                '}';
    }
}