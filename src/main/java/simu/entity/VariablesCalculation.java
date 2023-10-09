package simu.entity;

import simu.model.EventType;

import java.util.ArrayList;
import java.util.List;

public class VariablesCalculation {
    private static int checkInC; // checkIn variable C
    private static int bagDropC; // bagDrop variable C
    private static int passportCheckC; // passportCheck variable C
    private static int securityCheckC; // securityCheck variable C
    private static int ticketInspectionC; // ticketInspection variable C
    private static double checkInB; // checkIn variable B(usy time)
    private static double bagDropB; // bagDrop variable B(usy time)
    private static double passportCheckB; // passportCheck variable B(usy time)
    private static double securityCheckB; // securityCheck variable B(usy time)
    private static double ticketInspectionB; // ticketInspection variable B(usy time)

    private static double simulationTotalTime; // simulation total-time T

    private static final List<Double> checkInRi = new ArrayList<>();
    private static final List<Double> bagDropRi = new ArrayList<>();
    private static final List<Double> passportCheckRi = new ArrayList<>();
    private static final List<Double> securityCheckRi = new ArrayList<>();
    private static final List<Double> ticketInspectionRi = new ArrayList<>();


    /**
     * save total time of simulation
     *
     * @param duration simulation total-time T
     */
    public static void setSimulationTotalTime(double duration) {
        simulationTotalTime = duration;
    }

    public static void servicePointC(EventType eventType) {
        switch (eventType) {
            case CHECKIN:
                checkInC++;
                break;
            case BAGDROP:
                bagDropC++;
                break;
            case PASSPORTCHECK:
                passportCheckC++;
                break;
            case SECURITYCHECK:
                securityCheckC++;
                break;
            case TICKETINSPECTION:
                ticketInspectionC++;
                break;
        }
    }

    public static void servicePointB(double serviceTime, EventType eventType) {
        switch (eventType) {
            case CHECKIN:
                checkInB += serviceTime;
                break;
            case BAGDROP:
                bagDropB += serviceTime;
                break;
            case PASSPORTCHECK:
                passportCheckB += serviceTime;
                break;
            case SECURITYCHECK:
                securityCheckB += serviceTime;
                break;
            case TICKETINSPECTION:
                ticketInspectionB += serviceTime;
                break;
        }
    }

    public static void servicePointRi(double riEnd, double riStart, EventType eventType) {
        switch (eventType) {
            case CHECKIN:
                checkInRi.add(riEnd - riStart);
                break;
            case BAGDROP:
                bagDropRi.add(riEnd - riStart);
                break;
            case PASSPORTCHECK:
                passportCheckRi.add(riEnd - riStart);
                break;
            case SECURITYCHECK:
                securityCheckRi.add(riEnd - riStart);
                break;
            case TICKETINSPECTION:
                ticketInspectionRi.add(riEnd - riStart);
                break;
        }
    }

    public static List<Double> getRiList(EventType eventType) {
        return switch (eventType) {
            case CHECKIN -> checkInRi;
            case BAGDROP -> bagDropRi;
            case PASSPORTCHECK -> passportCheckRi;
            case SECURITYCHECK -> securityCheckRi;
            case TICKETINSPECTION -> ticketInspectionRi;
            default -> throw new IllegalArgumentException("Invalid event type" + eventType);
        };
    }


    public static double calculateUtilization(EventType eventType) { // U
        return switch (eventType) {
            case CHECKIN -> checkInB / simulationTotalTime;
            case BAGDROP -> bagDropB / simulationTotalTime;
            case PASSPORTCHECK -> passportCheckB / simulationTotalTime;
            case SECURITYCHECK -> securityCheckB / simulationTotalTime;
            case TICKETINSPECTION -> ticketInspectionB / simulationTotalTime;
            default -> throw new IllegalArgumentException("Invalid event type" + eventType);
        };
    }

    public static double calculateThroughput(EventType eventType) { // X
        return switch (eventType) {
            case CHECKIN -> checkInC / simulationTotalTime;
            case BAGDROP -> bagDropC / simulationTotalTime;
            case PASSPORTCHECK -> passportCheckC / simulationTotalTime;
            case SECURITYCHECK -> securityCheckC / simulationTotalTime;
            case TICKETINSPECTION -> ticketInspectionC / simulationTotalTime;
            default -> throw new IllegalArgumentException("Invalid event type" + eventType);
        };
    }

    public static double calculateAverageServiceTime(EventType eventType) { // S
        return switch (eventType) {
            case CHECKIN -> checkInB / checkInC;
            case BAGDROP -> bagDropB / bagDropC;
            case PASSPORTCHECK -> passportCheckB / passportCheckC;
            case SECURITYCHECK -> securityCheckB / securityCheckC;
            case TICKETINSPECTION -> ticketInspectionB / ticketInspectionC;
            default -> throw new IllegalArgumentException("Invalid event type" + eventType);
        };
    }

    public static double calculateTotalWaitingTime(EventType eventType) { // W
        double waitingTime = 0;
        List<Double> selectedRiList = switch (eventType) {
            case CHECKIN -> checkInRi;
            case BAGDROP -> bagDropRi;
            case PASSPORTCHECK -> passportCheckRi;
            case SECURITYCHECK -> securityCheckRi;
            case TICKETINSPECTION -> ticketInspectionRi;
            default -> throw new IllegalArgumentException("Invalid event type" + eventType);
        };

        for (Double aDouble : selectedRiList) {
            waitingTime += aDouble;
        }

        return waitingTime;
    }

    public static double calculateResponseTime(EventType eventType) { // R
        return switch (eventType) {
            case CHECKIN -> calculateTotalWaitingTime(EventType.CHECKIN) / checkInC;
            case BAGDROP -> calculateTotalWaitingTime(EventType.BAGDROP) / bagDropC;
            case PASSPORTCHECK -> calculateTotalWaitingTime(EventType.PASSPORTCHECK) / passportCheckC;
            case SECURITYCHECK -> calculateTotalWaitingTime(EventType.SECURITYCHECK) / securityCheckC;
            case TICKETINSPECTION -> calculateTotalWaitingTime(EventType.TICKETINSPECTION) / ticketInspectionC;
            default -> throw new IllegalArgumentException("Invalid event type" + eventType);
        };
    }

    public static double calculateAverageQueueLength(EventType eventType) { // N
        return switch (eventType) {
            case CHECKIN -> calculateTotalWaitingTime(EventType.CHECKIN) / simulationTotalTime;
            case BAGDROP -> calculateTotalWaitingTime(EventType.BAGDROP) / simulationTotalTime;
            case PASSPORTCHECK -> calculateTotalWaitingTime(EventType.PASSPORTCHECK) / simulationTotalTime;
            case SECURITYCHECK -> calculateTotalWaitingTime(EventType.SECURITYCHECK) / simulationTotalTime;
            case TICKETINSPECTION -> calculateTotalWaitingTime(EventType.TICKETINSPECTION) / simulationTotalTime;
            default -> throw new IllegalArgumentException("Invalid event type" + eventType);
        };
    }

    public static void reset() {
        checkInC = 0;
        bagDropC = 0;
        passportCheckC = 0;
        securityCheckC = 0;
        ticketInspectionC = 0;
        checkInB = 0;
        bagDropB = 0;
        passportCheckB = 0;
        securityCheckB = 0;
        ticketInspectionB = 0;
        checkInRi.clear();
        bagDropRi.clear();
        passportCheckRi.clear();
        securityCheckRi.clear();
        ticketInspectionRi.clear();
    }

    public static Variables[] getVariablesAsObject() {
        int NUM_OF_EVENT_TYPES = 5;
        Variables[] variables = new Variables[NUM_OF_EVENT_TYPES]; // 5 is num of event types
        variables[0] = new Variables("Check-in", checkInC, simulationTotalTime, checkInB, calculateUtilization(EventType.CHECKIN), calculateThroughput(EventType.CHECKIN), calculateAverageServiceTime(EventType.CHECKIN), calculateTotalWaitingTime(EventType.CHECKIN), calculateResponseTime(EventType.CHECKIN), calculateAverageQueueLength(EventType.CHECKIN));
        variables[1] = new Variables("Bag-drop", bagDropC, simulationTotalTime, bagDropB, calculateUtilization(EventType.BAGDROP), calculateThroughput(EventType.BAGDROP), calculateAverageServiceTime(EventType.BAGDROP), calculateTotalWaitingTime(EventType.BAGDROP), calculateResponseTime(EventType.BAGDROP), calculateAverageQueueLength(EventType.BAGDROP));
        variables[2] = new Variables("Passport-check", passportCheckC, simulationTotalTime, passportCheckB, calculateUtilization(EventType.PASSPORTCHECK), calculateThroughput(EventType.PASSPORTCHECK), calculateAverageServiceTime(EventType.PASSPORTCHECK), calculateTotalWaitingTime(EventType.PASSPORTCHECK), calculateResponseTime(EventType.PASSPORTCHECK), calculateAverageQueueLength(EventType.PASSPORTCHECK));
        variables[3] = new Variables("Security-check", securityCheckC, simulationTotalTime, securityCheckB, calculateUtilization(EventType.SECURITYCHECK), calculateThroughput(EventType.SECURITYCHECK), calculateAverageServiceTime(EventType.SECURITYCHECK), calculateTotalWaitingTime(EventType.SECURITYCHECK), calculateResponseTime(EventType.SECURITYCHECK), calculateAverageQueueLength(EventType.SECURITYCHECK));
        variables[4] = new Variables("Ticket-inspection", ticketInspectionC, simulationTotalTime, ticketInspectionB, calculateUtilization(EventType.TICKETINSPECTION), calculateThroughput(EventType.TICKETINSPECTION), calculateAverageServiceTime(EventType.TICKETINSPECTION), calculateTotalWaitingTime(EventType.TICKETINSPECTION), calculateResponseTime(EventType.TICKETINSPECTION), calculateAverageQueueLength(EventType.TICKETINSPECTION));
        return variables;
    }

}
