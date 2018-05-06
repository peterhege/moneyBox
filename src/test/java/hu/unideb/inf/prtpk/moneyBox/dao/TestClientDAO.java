package hu.unideb.inf.prtpk.moneyBox.dao;

import hu.unideb.inf.prtpk.moneyBox.dao.api.ClientDAO;
import hu.unideb.inf.prtpk.moneyBox.model.Product;
import hu.unideb.inf.prtpk.moneyBox.model.Client;
import hu.unideb.inf.prtpk.moneyBox.utility.EntityManagerFactoryUtil;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.xml.transform.stream.StreamResult;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestClientDAO {
    private ClientDAO clientDAO = new ClientDAOImpl();
    private EntityManager entityManager;

    private Product product = new Product(
            "Huawei Watch okosóra fém tokkal és fémháló szíjjal",
            "https://edigital.hu/okosora/huawei-watch-okosora-fem-tokkal-es-femhalo-szijjal-p408376",
            129990
    );

    private Client user = new Client(
            "user",
            "password",
            "user@mail.com"
    );

    private void clearAllTable() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Client").executeUpdate();
        entityManager.createQuery("DELETE FROM Product").executeUpdate();
        entityManager.getTransaction().commit();
    }

    {
        user.addProduct(product);
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryUtil.getInstance().getEntityManagerFactory();
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Test
    public void testPersist() {
        clientDAO.persist(user);
        List<Client> clients = clientDAO.getAll();

        assertEquals(1, clients.size());

        clearAllTable();
    }

    @Test
    public void testRemove() {
        clientDAO.persist(user);
        List<Client> clients = clientDAO.getAll();
        clientDAO.remove(clients.get(0));
        clients = clientDAO.getAll();

        assertEquals(0, clients.size());

        clearAllTable();
    }

    @Test
    public void testFindById() {
        clientDAO.persist(user);
        List<Client> clients = clientDAO.getAll();

        Client createdClient = clients.get(0);
        Long id = createdClient.getId();

        Client foundClient = clientDAO.findById(id).get();

        assertEquals(createdClient, foundClient);

        clearAllTable();
    }

    @Test
    public void testFindByName() {
        clientDAO.persist(user);
        List<Client> clients = clientDAO.getAll();

        Client createdClient = clients.get(0);
        String name = createdClient.getClientName();

        Client foundClient = clientDAO.findByName(name).get();

        assertEquals(createdClient, foundClient);

        clearAllTable();
    }

    @Test
    public void testFindByEmail() {
        clientDAO.persist(user);
        List<Client> clients = clientDAO.getAll();

        Client createdClient = clients.get(0);
        String email = createdClient.getEmail();

        Client foundClient = clientDAO.findByEmail(email).get();

        assertEquals(createdClient, foundClient);

        clearAllTable();
    }
}
