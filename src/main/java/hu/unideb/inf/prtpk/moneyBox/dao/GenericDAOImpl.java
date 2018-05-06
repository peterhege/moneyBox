package hu.unideb.inf.prtpk.moneyBox.dao;

import hu.unideb.inf.prtpk.moneyBox.dao.api.GenericDAO;
import hu.unideb.inf.prtpk.moneyBox.utility.EntityManagerFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>Implementálja a DAO-k közös metódusait.</pre>
 *
 * @param <T>  A DAO-hoz tartozó entitás
 * @param <ID> Az entitás azonosítója
 */
public class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {

    /**
     * <pre>LoggerFactory a logoláshoz.</pre>
     */
    private static Logger logger = LoggerFactory.getLogger(GenericDAOImpl.class);

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
        logger.info("Create EntityManager.");
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryUtil.getInstance().getEntityManagerFactory();
        entityManager = entityManagerFactory.createEntityManager();
        this.type = type;
    }

    @Override
    public void persist(T entity) {
        logger.info("Persist " + type.getName() + " entity:\n" + entity);
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public Optional<T> findById(ID id) {
        logger.info("Search " + type.getName() + " entity by ID: " + id);
        return Optional.of(entityManager.find(type, id));
    }

    @Override
    public Optional<T> findBy(String entityParamName, String entityParam){
        logger.info("Search " + type.getName() + " entity by " + entityParamName + ": " + entityParam);
        try{
            TypedQuery<T> query = entityManager.createQuery("SELECT c FROM " + type.getName() + " c WHERE c." + entityParamName + " LIKE :entityParam", type).setParameter("entityParam", entityParam);
            return Optional.of(query.getSingleResult());
        }
        catch (NoResultException e){
            logger.info("The " + type.getName() + " doesn't exist");
            return Optional.empty();
        }


    }

    @Override
    public void remove(T entity) {
        logger.info("Remove " + type.getName() + " entity:\n" + entity);
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<T> getAll() {
        logger.info("Search all " + type.getName() + " entity");
        TypedQuery<T> query = entityManager.createQuery("SELECT t FROM " + type.getName() + " t", type);
        return query.getResultList();
    }
}
