package simu.model;

import simu.framework.Clock;
import simu.framework.Trace;

public class Customer {
    private double arrivalTime;
    private double departureTime;
    private final int id;
    private static int nextAvailableId = 1;
    private static double totalCustomerStayTime = 0;
    private boolean isOnlineCheckedIn, isLateFromFlight, hasBaggage;

    private boolean flyOutOfEurope;

    public Customer() {
        this.id = nextAvailableId++;
        this.arrivalTime = Clock.getInstance().getClock();
        this.hasBaggage = false; // TODO: needs arrival method to determine if customer has baggage
        this.isOnlineCheckedIn = false; // TODO: needs method to determine if customer is online checked in
        this.isLateFromFlight = false; // TODO: needs ServicePoint to determine if customer is late from flight

        Trace.out(Trace.Level.INFO, "New Customer nbr " + id + " arrived at " + arrivalTime);
    }

    public double getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(double departureTime) {
        this.departureTime = departureTime;
        totalCustomerStayTime += (calculateStayTime());
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

    public boolean getWillFlyOutOfEurope() {
        return this.flyOutOfEurope;
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

    // Boolean getters and setters


    public boolean isOnlineCheckedIn() {
        return isOnlineCheckedIn;
    }

    public void setOnlineCheckedIn(boolean isOnlineCheckedIn) {
        this.isOnlineCheckedIn = isOnlineCheckedIn;
    }


    public boolean isLateFromFlight() {
        return isLateFromFlight;
    }

    public void setLateFromFlight(boolean lateFromFlight) {
        isLateFromFlight = lateFromFlight;
    }

    public static double getTotalCustomerStayTime() {
        return totalCustomerStayTime;
    }

    public boolean isHasBaggage() {
        return hasBaggage;
    }

    public void setHasBaggage(boolean hasBaggage) {
        this.hasBaggage = hasBaggage;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", id=" + id +
                ", isOnlineCheckedIn=" + isOnlineCheckedIn +
                ", isLateFromFlight=" + isLateFromFlight +
                ", hasBaggage=" + hasBaggage +
                '}';
    }
}