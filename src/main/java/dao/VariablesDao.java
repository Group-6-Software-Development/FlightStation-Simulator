package dao;

import datasource.flightDbConnection;
import jakarta.persistence.EntityManager;
import simu.entity.Variables;

public class VariablesDao {
    private final EntityManager em = flightDbConnection.getInstance();

    public void persistVariables(Variables[] variablesArray) {
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
}
