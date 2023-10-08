package dao;

import datasource.flightDbConnection;
import jakarta.persistence.EntityManager;
import simu.entity.VariablesCalculation;
import simu.entity.Variables;

public class VariablesDao {
    private final EntityManager em = flightDbConnection.getInstance();

    public void insertVariables(Variables[] variablesArray) {
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
