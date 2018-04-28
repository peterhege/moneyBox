package hu.unideb.inf.prtpk.moneyBox.utility;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Erőforrás spórolás | Resource saving
 */
public class EntityManagerFactoryUtil implements AutoCloseable {
    private static final EntityManagerFactoryUtil singleton = new EntityManagerFactoryUtil();
    private static final String PERSISTENCE_UNIT_NAME = "money-box-persistence-unit";

    private EntityManagerFactory entityManagerFactory;

    /**
     * Üres konstruktor | Empty constructor
     */
    private EntityManagerFactoryUtil() {
    }

    /**
     * Példányosítás | Make an instance
     * @return EntityManagerFactoryUtil egyetlen példánya | Single Instance of EntityManagerFactoryUtil
     */
    public static EntityManagerFactoryUtil getInstance() {
        return singleton;
    }

    /**
     * Az EntityManagerFactory perzisztálása, ha nem létezik | If EntityManagerFactory not exist than persist it
     * @return entityManagerFactory
     */
    public EntityManagerFactory getEntityManagerFactory() {
        /*Map<String, String> properties = new HashMap<String, String>();
        properties.put("javax.persistence.jdbc.password", "");*/
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return entityManagerFactory;
    }

    /**
     * A példány lezárása | Close the instance
     */
    @Override
    public void close() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
