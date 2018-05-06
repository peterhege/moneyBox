package hu.unideb.inf.prtpk.moneyBox.service;

import hu.unideb.inf.prtpk.moneyBox.dao.ClientDAOImpl;
import hu.unideb.inf.prtpk.moneyBox.dao.api.ClientDAO;
import hu.unideb.inf.prtpk.moneyBox.model.Client;
import hu.unideb.inf.prtpk.moneyBox.service.api.EntityService;
import hu.unideb.inf.prtpk.moneyBox.utility.EntityManagerFactoryUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class TestEntityService {
    private EntityManager entityManager;
    private ClientDAO clientDAO = new ClientDAOImpl();
    private EntityService entityService = new EntityServiceImpl(clientDAO);
    private Client user = new Client(
            "user",
            "password",
            "user@mail.com"
    );

    private Client userWithInvalidName = new Client(
            "a",
            "password",
            "user@mail.com"
    );

    private Client userWithInvalidPassword = new Client(
            "user",
            "a",
            "user@mail.com"
    );

    private Client userWithInvalidEmail = new Client(
            "user",
            "password",
            "@mail.com"
    );

    private void clearAllTable() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Client").executeUpdate();
        entityManager.createQuery("DELETE FROM Product").executeUpdate();
        entityManager.getTransaction().commit();
    }

    {
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryUtil.getInstance().getEntityManagerFactory();
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Test
    public void testCreateClient() {
        entityService.createClient(user);

        List<Client> clients = clientDAO.getAll();

        assertEquals(1, clients.size());

        clearAllTable();
    }

    @Test
    public void testCreateInvalidClient() {
        entityService.createClient(userWithInvalidName);
        entityService.createClient(userWithInvalidPassword);
        entityService.createClient(userWithInvalidEmail);

        List<Client> clients = clientDAO.getAll();

        assertEquals(0, clients.size());

        clearAllTable();
    }

    @Test
    public void testCreateExistClient(){
        entityService.createClient(user);
        entityService.createClient(user);

        List<Client> clients = clientDAO.getAll();

        assertEquals(1, clients.size());

        clearAllTable();
    }

    @Test
    public void testRemoveClient(){
        entityService.createClient(user);
        Client client = clientDAO.findByName(user.getClientName()).get();
        entityService.removeClient(client.getId());

        List<Client> clients = clientDAO.getAll();

        assertEquals(0, clients.size());

        clearAllTable();
    }

    @Test
    public void testRemoveNotExistClient(){
        try{
            entityService.removeClient(1L);
        }
        catch (EntityNotFoundException e){
            System.out.println(e);
        }

        List<Client> clients = clientDAO.getAll();

        assertEquals(0, clients.size());

        clearAllTable();
    }
}
