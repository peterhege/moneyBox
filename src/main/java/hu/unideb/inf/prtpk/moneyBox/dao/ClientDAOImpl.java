package hu.unideb.inf.prtpk.moneyBox.dao;

import hu.unideb.inf.prtpk.moneyBox.dao.api.ClientDAO;
import hu.unideb.inf.prtpk.moneyBox.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Optional;

/**
 * <pre>ClientDAO implementálása.</pre>
 *
 * @see ClientDAO
 */
public class ClientDAOImpl extends GenericDAOImpl<Client, Long> implements ClientDAO {
    /**
     * <pre>LoggerFactory a logoláshoz.</pre>
     */
    private static Logger logger = LoggerFactory.getLogger(ClientDAOImpl.class);

    /**
     * <pre>Konstruktor, mely átadja az osztály típusát.</pre>
     */
    public ClientDAOImpl() {
        super(Client.class);
    }

    @Override
    public Optional<Client> findByName(String userName) {
        logger.info("Search " + Client.class.getName() + " entity by User name: " + userName);
        return findBy("userName", userName);
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        logger.info("Search " + Client.class.getName() + " entity by email: " + email);
        return findBy("email", email);
    }

    @Override
    public Optional<Client> findByNameAndPass(String userName, String password) {
        logger.info("Search Client by User Name and Password.");
        try {
            TypedQuery<Client> query = entityManager.createQuery("SELECT c FROM " + Client.class.getName() + " c WHERE c.userName LIKE :userName AND c.password LIKE :password", Client.class).setParameter("userName", userName).setParameter("password", password);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            logger.info("The " + Client.class.getName() + " doesn't exist");
            return Optional.empty();
        }
    }
}