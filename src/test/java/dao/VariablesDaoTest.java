package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import datasource.flightDbConnection;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import simu.entity.Variables;

import java.util.List;

public class VariablesDaoTest {
    private EntityManager em;
    private VariablesDao variablesDao;

    @BeforeEach
    public void setUp() {
        em = flightDbConnection.getInstance();
        variablesDao = new VariablesDao();
    }

    @AfterEach
    public void tearDown() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

    @Test
    public void testGetAllVariables() {
        Variables sampleVariables = new Variables("ServicePoint4", 15, 140, 119, 0.85, 140, 12, 23, 33, 7);
        em.getTransaction().begin();
        em.persist(sampleVariables);
        em.getTransaction().commit();

        List<Variables> results = variablesDao.getAllVariables();

        assertNotNull(results);
        assertEquals(1, results.size());
        Variables retrievedVariables = results.get(0);
        assertEquals("ServicePoint4", retrievedVariables.getName());
    }
}
