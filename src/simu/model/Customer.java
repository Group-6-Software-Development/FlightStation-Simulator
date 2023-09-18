package simu.model;

import simu.framework.Clock;
import simu.framework.Trace;

public class Customer {
    private double arrivalTime;
    private double departureTime;
    private final int id;
    private static int nextAvailableId = 1;
    private static double totalCustomerStayTime = 0;
    private boolean isPriority, isOnlineCheckedIn, isNormalCheckedIn, isSelfCheckedIn, isBaggageDropped, isSecurityChecked, isLateFromFlight, hasBaggage;

    public Customer() {
        this.id = nextAvailableId++;
        this.arrivalTime = Clock.getInstance().getClock();
        this.hasBaggage = false; // TODO: needs arrival method to determine if customer has baggage
        // this.isPriority = false; // TODO: needs method to determine if customer is priority
        this.isOnlineCheckedIn = false; // TODO: needs method to determine if customer is online checked in
        // this.isSelfCheckedIn = false; // TODO: needs method to determine if customer is self checked in
        // this.isNormalCheckedIn = false; // TODO: needs servicePoint to flip this to true when customer is checked in
        // this.isBaggageDropped = false; // TODO: servicePoint needs to flip this to true when customer has dropped baggage
        // this.isSecurityChecked = false; // TODO: servicePoint needs to flip this to true when customer is security checked
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
    public boolean isPriority() {
        return isPriority;
    }
    public void setPriority(boolean isPriority) {
        this.isPriority = isPriority;
    }

    public boolean isOnlineCheckedIn() {
        return isOnlineCheckedIn;
    }
    public void setOnlineCheckedIn(boolean isOnlineCheckedIn) {
        this.isOnlineCheckedIn = isOnlineCheckedIn;
    }

    public boolean isSelfCheckedIn() {
        return isSelfCheckedIn;
    }
    public void setSelfCheckedIn(boolean isSelfCheckedIn) {
        this.isSelfCheckedIn = isSelfCheckedIn;
    }

    public boolean isNormalCheckedIn() {
        return isNormalCheckedIn;
    }

    public void setNormalCheckedIn(boolean normalCheckedIn) {
        this.isNormalCheckedIn = normalCheckedIn;
    }

    public boolean isBaggageDropped() {
        return isBaggageDropped;
    }

    public void setBaggageDropped(boolean baggageDropped) {
        isBaggageDropped = baggageDropped;
    }

    public boolean isSecurityChecked() {
        return isSecurityChecked;
    }

    public void setSecurityChecked(boolean securityChecked) {
        isSecurityChecked = securityChecked;
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
                ", isPriority=" + isPriority +
                ", isOnlineCheckedIn=" + isOnlineCheckedIn +
                ", isNormalCheckedIn=" + isNormalCheckedIn +
                ", isSelfCheckedIn=" + isSelfCheckedIn +
                ", isBaggageDropped=" + isBaggageDropped +
                ", isSecurityChecked=" + isSecurityChecked +
                ", isLateFromFlight=" + isLateFromFlight +
                ", hasBaggage=" + hasBaggage +
                '}';
    }
}