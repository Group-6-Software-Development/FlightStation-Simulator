package datasource;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * This class is used to get EntityManager instance
 */
// Singleton pattern
public class flightDbConnection {
    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;

    /**
     * Private constructor to prevent instantiation
     */
    private flightDbConnection() {
        // empty constructor
    }

    /**
     * Method to get EntityManager instance
     *
     * @return EntityManager instance
     */
    public static EntityManager getInstance() {
        // you need to add synchronization if you run in a multithreaded environment

        if (em == null) {
            if (emf == null) {
                emf = Persistence.createEntityManagerFactory("FlightStationMariaDbUnit");
            }
            em = emf.createEntityManager();
        }
        return em;
    }
}
