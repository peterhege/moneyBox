package hu.unideb.inf.prtpk.moneyBox.controller;

import hu.unideb.inf.prtpk.moneyBox.dao.ClientDAOImpl;
import hu.unideb.inf.prtpk.moneyBox.dao.api.ClientDAO;
import hu.unideb.inf.prtpk.moneyBox.model.Client;
import hu.unideb.inf.prtpk.moneyBox.utility.EntityManagerFactoryUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestClientController {

    private ClientDAO clientDAO = new ClientDAOImpl();

    private EntityManager entityManager = EntityManagerFactoryUtil.getInstance().getEntityManagerFactory().createEntityManager();

    private Client user = new Client(
            "user",
            "password",
            "user@mail.com"
    );

    private void clearAllTable() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Product").executeUpdate();
        entityManager.createQuery("DELETE FROM Client").executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Test
    public void testRegistration() {
        ClientController clientController = new ClientController();
        clientController.setUserName(user.getClientName());
        clientController.setEmail(user.getEmail());
        clientController.setPassword(user.getPassword());
        clientController.registration();

        List<Client> clients = clientDAO.getAll();

        assertEquals(1, clients.size());

        clearAllTable();
    }
}
