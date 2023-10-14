package datasource;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class flightdbconnectiontest {
    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        entityManager = flightDbConnection.getInstance();
    }

    @AfterEach
    public void tearDown() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }

    @Test
    public void testGetInstance() {
        assertNotNull(entityManager);
    }
}
