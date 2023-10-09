package dao;

import datasource.flightDbConnection;
import jakarta.persistence.EntityManager;
import simu.entity.Variables;

import java.util.List;

public class VariablesDao {
    private final EntityManager em = flightDbConnection.getInstance();

    public void persistVariablesResults(Variables[] variablesArray) {
        try {
            em.getTransaction().begin();
            for (Variables variables : variablesArray) {
                em.persist(variables);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.printf("Error: %s%n", e.getMessage());
        }
    }

    /**
     * @param name service point name is to be given to get its variables
     * @return name, arrivalCount, utilization, totalTime, busyTime, avgServiceTime from database as a list of variables
     */
    public List<Variables> getVariablesByServicePointName(String name) {
        return em.createQuery("SELECT name, arrivalCount, utilization, totalTime, busyTime, avgServiceTime  FROM Variables v WHERE v.name = :servicePointName", Variables.class)
                .setParameter("servicePointName", name)
                .getResultList();
    }

    /**
     * @return all variables from database as a list of variables
     */
    public List<Variables> getAllVariables() {
        return em.createQuery("SELECT v FROM Variables v", Variables.class)
                .getResultList();
    }
}
