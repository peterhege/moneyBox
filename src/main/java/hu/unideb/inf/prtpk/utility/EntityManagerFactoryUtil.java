package hu.unideb.inf.prtpk.utility;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * Erőforrás spórolás | Resource saving
 */
public class EntityManagerFactoryUtil {
    private static final EntityManagerFactoryUtil singleton = new EntityManagerFactoryUtil();
    private static final String PERSISTENCE_UNIT_NAME = "money-box-persistence-unit";

    private EntityManagerFactory entityManagerFactory;

    private EntityManagerFactoryUtil() {
    }

    public EntityManagerFactoryUtil getInstance() {
        return singleton;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        /*Map<String, String> properties = new HashMap<String, String>();
        properties.put("javax.persistence.jdbc.password", "talicska");*/
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return entityManagerFactory;
    }

    public void close() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
