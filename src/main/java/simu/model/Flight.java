package simu.model;

public class Flight {
    private final String flightNumber;
    private final long departureTime;
    private final int seatCount;

    public Flight(String flightNumber, long departureTime, int seatCount) {
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.seatCount = seatCount;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public long getDepartureTime() {
        return departureTime;
    }

    public int getSeatCount() {
        return seatCount;
    }
}
