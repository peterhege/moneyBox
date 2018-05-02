package hu.unideb.inf.prtpk.moneyBox.dao;

import hu.unideb.inf.prtpk.moneyBox.dao.api.GenericDAO;
import hu.unideb.inf.prtpk.moneyBox.utility.EntityManagerFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * <pre>Implementálja a DAO-k közös metódusait.</pre>
 *
 * @param <T>
 * @param <ID>
 */
public class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {
    /**
     * <pre>EntityManager előkészítése.</pre>
     */
    private EntityManager entityManager;

    /**
     * <pre>A kiterjesztett DAO típusa.</pre>
     */
    private final Class<T> type;

    /**
     * <pre>Konstruktor, mely elkészíti az EntityManager-t.</pre>
     *
     * @param type {@link #type}
     * @see EntityManagerFactoryUtil
     */
    GenericDAOImpl(Class<T> type) {
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryUtil.getInstance().getEntityManagerFactory();
        entityManager = entityManagerFactory.createEntityManager();
        this.type = type;
    }

    @Override
    public void persist(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.of(entityManager.find(type, id));
    }

    @Override
    public void remove(T entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<T> getAll() {
        TypedQuery<T> query = entityManager.createQuery("SELECT t FROM " + type.getName() + " t", type);
        return query.getResultList();
    }
}
