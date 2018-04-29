package hu.unideb.inf.prtpk.moneyBox.dao;

import hu.unideb.inf.prtpk.moneyBox.dao.api.ClientDAO;
import hu.unideb.inf.prtpk.moneyBox.model.Client;
import hu.unideb.inf.prtpk.moneyBox.utility.EntityManagerFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * <pre>ClientDAO implementálása.</pre>
 *
 * @see ClientDAO
 */
public class ClientDAOImpl implements ClientDAO {
    /**
     * <pre>EntityManager előkészítése.</pre>
     */
    private EntityManager entityManager;

    /**
     * <pre>Konstruktor, mely elkészíti az EntityManager-t.</pre>
     *
     * @see EntityManagerFactoryUtil
     */
    public ClientDAOImpl() {
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryUtil.getInstance().getEntityManagerFactory();
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void persist(Client entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public Optional<Client> findById(Long id) {
        return Optional.of(entityManager.find(Client.class, id));
    }

    @Override
    public void remove(Client entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Client> getAllClient() {
        TypedQuery<Client> query = entityManager.createQuery("SELECT c FROM hu.unideb.inf.prtpk.moneyBox.model.Client c", Client.class);
        return query.getResultList();
    }
}
