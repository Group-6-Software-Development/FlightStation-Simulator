package simu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "variables")
public class Variables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column(name = "customer_count")
    private int arrivalCount; // A
    @Column(name = "total_time")
    private double totalTime; // T
    @Column(name = "busy_time")
    private double busyTime; // B
    @Column(name = "utilization")
    private double Utilization; // U = B/T
    @Column(name = "through_put")
    private double throughPut; // X = C/T
    @Column(name = "avg_service_time")
    private double avgServiceTime; // S = B/C
    @Column(name = "waiting_time")
    private double waitingTime; // W
    @Column(name = "lead_time")
    private double leadTime; // R = W/C lapimenoaika
    @Column(name = "queue_length")
    private double queueLength; // N


    public Variables(String name, int arrivalCount, double totalTime, double busyTime, double utilization, double throughPut, double avgServiceTime, double waitingTime, double leadTime, double queueLength) {
        this.name = name;
        this.arrivalCount = arrivalCount;
        this.totalTime = totalTime;
        this.busyTime = busyTime;
        this.Utilization = utilization;
        this.throughPut = throughPut;
        this.avgServiceTime = avgServiceTime;
        this.waitingTime = waitingTime;
        this.leadTime = leadTime;
        this.queueLength = queueLength;
    }

    public Variables() {
    }

    /**
     * @return service point id
     */
    public int getId() {
        return id;
    }

    /**
     * @return service point name
     */
    public String getName() {
        return name;
    }


    /**
     * @return service point arrival count
     */
    public int getArrivalCount() {
        return arrivalCount;
    }

    /**
     * @return simulation total time
     */
    public double getTotalTime() {
        return totalTime;
    }


    /**
     * @return service point busy time
     */
    public double getBusyTime() {
        return busyTime;
    }


    /**
     * @return service point utilization (B/T)
     */
    public double getUtilization() {
        return Utilization;
    }

    /**
     * @return throughput (C/T)
     */
    public double getThroughPut() {
        return throughPut;
    }

    /**
     * @return average service time (B/C)
     */
    public double getAvgServiceTime() {
        return avgServiceTime;
    }

    /**
     * @return customers' total waiting time
     */
    public double getWaitingTime() {
        return waitingTime;
    }


    /**
     * @return average customer through time (W/C)
     */
    public double getLeadTime() {
        return leadTime;
    }


    /**
     * @return average queue length (N = W/T)
     */
    public double getQueueLength() {
        return queueLength;
    }


    @Override
    public String toString() {
        return "Variables{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", arrivalCount=" + arrivalCount +
                ", totalTime=" + totalTime +
                ", busyTime=" + busyTime +
                ", Utilization=" + Utilization +
                ", throughPut=" + throughPut +
                ", avgServiceTime=" + avgServiceTime +
                ", waitingTime=" + waitingTime +
                ", leadTime=" + leadTime +
                ", queueLength=" + queueLength +
                '}';
    }
}
