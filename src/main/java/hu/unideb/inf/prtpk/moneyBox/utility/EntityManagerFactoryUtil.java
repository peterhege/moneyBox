package hu.unideb.inf.prtpk.moneyBox.utility;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * <pre>Erőforrás spórolását elősegítő osztály.</pre>
 */
public class EntityManagerFactoryUtil implements AutoCloseable {
    /**
     * Az osztály példányosítása.
     */
    private static final EntityManagerFactoryUtil singleton = new EntityManagerFactoryUtil();

    /**
     * A persistence.xml-ben megadott persistence-unit tag name attribútuma.
     */
    private static final String PERSISTENCE_UNIT_NAME = "money-box-persistence-unit";

    /**
     * EntityManagerFactory.
     */
    private EntityManagerFactory entityManagerFactory;

    /**
     * <pre>Üres konstruktor.</pre>
     */
    private EntityManagerFactoryUtil() {
    }

    /**
     * <pre>Visszatér a példánnyal.</pre>
     *
     * @return {@link #singleton}
     */
    public static EntityManagerFactoryUtil getInstance() {
        return singleton;
    }

    /**
     * <pre>Az EntityManagerFactory perzisztálása, ha még nem történt meg.</pre>
     *
     * @return {@link #entityManagerFactory}
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
     * <pre>A példány lezárása.</pre>
     */
    @Override
    public void close() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
