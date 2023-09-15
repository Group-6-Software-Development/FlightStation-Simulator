package simu.model;

import simu.framework.Clock;
import simu.framework.Trace;

public class Customer {
    private double arrivalTime;
    private double departureTime;
    private final int id;
    private static int nextAvailableId = 1;
    private static double totalCustomerStayTime = 0;
    private boolean isPriority;

    public Customer() {
        this.id = nextAvailableId++;
        this.arrivalTime = Clock.getInstance().getClock();
        this.isPriority = false; // TODO: needs method to determine if customer is priority
        Trace.out(Trace.Level.INFO, "New Customer nbr " + id + " arrived at " + arrivalTime);
    }

    public double getDepartureTime() {
        return departureTime;
    }

    public boolean isPriority() {
        return isPriority;
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

    public void report() {
        Trace.out(Trace.Level.INFO, "\nCustomer " + id + " ready! ");
        Trace.out(Trace.Level.INFO, "Customer " + id + " arrived: " + arrivalTime);
        Trace.out(Trace.Level.INFO, "Customer " + id + " departed: " + departureTime);
        Trace.out(Trace.Level.INFO, "Customer " + id + " stayed: " + (departureTime - arrivalTime));
        totalCustomerStayTime += (departureTime - arrivalTime);
        double averageStayTime = totalCustomerStayTime / id;
        System.out.println("Average time of customers' stay so far " + averageStayTime);
    }




    @Override
    public String toString() {
        return "Customer{" +
                "arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", id=" + id +
                ", isPriority=" + isPriority +
                '}';
    }
}