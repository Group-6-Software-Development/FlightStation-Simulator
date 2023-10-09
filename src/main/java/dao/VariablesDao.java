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

    public List<Variables> getVariablesByServicePointName(String name) {
        return em.createQuery("SELECT name, utilization, arrivalCount, totalTime, busyTime  FROM Variables v WHERE v.name = :servicePointName", Variables.class)
                .setParameter("servicePointName", name)
                .getResultList();
    }
}
