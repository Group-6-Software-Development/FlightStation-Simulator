package datasource;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

// Singleton pattern
public class flightDbConnection {
    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;

    private flightDbConnection() {
        // empty constructor
    }

    public static EntityManager getInstance() {
        // you need to add synchronization if you run in a multithreaded environment

        if (em == null) {
            if (emf == null) {
                emf = Persistence.createEntityManagerFactory("FlightUnit");
            }
            em = emf.createEntityManager();
        }
        return em;
    }

}
