package hu.unideb.inf.prtpk.moneyBox.service;

import hu.unideb.inf.prtpk.moneyBox.dao.ClientDAOImpl;
import hu.unideb.inf.prtpk.moneyBox.dao.ProductDAOImpl;
import hu.unideb.inf.prtpk.moneyBox.dao.api.ClientDAO;
import hu.unideb.inf.prtpk.moneyBox.dao.api.ProductDAO;
import hu.unideb.inf.prtpk.moneyBox.model.Client;
import hu.unideb.inf.prtpk.moneyBox.model.Product;
import hu.unideb.inf.prtpk.moneyBox.service.api.EntityService;
import hu.unideb.inf.prtpk.moneyBox.utility.EntityManagerFactoryUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestEntityService {
    private EntityManager entityManager;
    private ClientDAO clientDAO = new ClientDAOImpl();
    private ProductDAO productDAO = new ProductDAOImpl();
    private EntityService entityService = new EntityServiceImpl(clientDAO, productDAO);
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

    private Product product = new Product(
            "Huawei Watch okosóra fém tokkal és fémháló szíjjal",
            "https://edigital.hu/okosora/huawei-watch-okosora-fem-tokkal-es-femhalo-szijjal-p408376",
            129990
    );

    private void clearAllTable() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Product").executeUpdate();
        entityManager.createQuery("DELETE FROM Client").executeUpdate();
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
    public void testCreateExistClient() {
        entityService.createClient(user);
        entityService.createClient(user);

        List<Client> clients = clientDAO.getAll();

        assertEquals(1, clients.size());

        clearAllTable();
    }

    @Test
    public void testRemoveClient() {
        entityService.createClient(user);
        clientDAO.findByName(user.getClientName()).ifPresent(c -> entityService.removeClient(c.getId()));

        List<Client> clients = clientDAO.getAll();

        assertEquals(0, clients.size());

        clearAllTable();
    }

    @Test
    public void testRemoveNotExistClient() {
        try {
            entityService.removeClient(1L);
        } catch (EntityNotFoundException e) {
            System.out.println(e.toString());
        }

        List<Client> clients = clientDAO.getAll();

        assertEquals(0, clients.size());

        clearAllTable();
    }

    @Test
    public void testUpdateClient() {
        Client updatableClient = new Client();
        Client updatedClient = new Client();

        entityService.createClient(user);

        Optional<Client> optionalClient = clientDAO.findByName(user.getClientName());
        if (optionalClient.isPresent()) updatableClient = optionalClient.get();
        updatableClient.setClientName("mrcsempe");

        entityService.updateClient(updatableClient);

        optionalClient = clientDAO.findById(updatableClient.getId());
        if(optionalClient.isPresent()) updatedClient = optionalClient.get();

        assertEquals(updatableClient, updatedClient);

        clearAllTable();
    }

    @Test
    public void testUpdateNotExistClient() {
        Client updatableClient = user;
        updatableClient.setId(1L);
        entityService.updateClient(updatableClient);

        List<Client> clients = clientDAO.getAll();

        assertEquals(0, clients.size());

        clearAllTable();
    }

    @Test
    public void testUpdateInvalidClient() {
        entityService.createClient(user);

        Client client = new Client();
        Optional<Client> optionalClient = clientDAO.findByName(user.getClientName());
        if(optionalClient.isPresent()) client = optionalClient.get();
        client.setClientName("b");
        client.setPassword("a");
        client.setEmail("a");
        entityService.updateClient(client);

        Client clientX = new Client();
        optionalClient = clientDAO.findById(client.getId());
        if(optionalClient.isPresent()) clientX = optionalClient.get();

        assertEquals(user, clientX);

        clearAllTable();
    }

    @Test
    public void testCreateProduct() {
        entityService.createClient(user);
        entityService.createAndAddProductToClient(user, product);

        List<Product> products = productDAO.getAll();

        assertEquals(1, products.size());
        assertEquals(product, user.getProducts().get(0));

        clearAllTable();
    }

    @Test
    public void testCreateProductToNotExistClient() {
        Client phantomClient = user;
        phantomClient.setId(1L);
        entityService.createAndAddProductToClient(phantomClient, product);

        List<Product> products = productDAO.getAll();

        assertEquals(0, products.size());

        clearAllTable();
    }

    @Test
    public void testFindClientByNameAndPass() {
        EntityService entityService = new EntityServiceImpl();
        entityService.createClient(user);

        Optional<Client> clientOptional = entityService.findClientByNameAndPass(user.getClientName(), user.getPassword());
        assertEquals(Optional.of(user), clientOptional);

        Optional<Client> clientOptional2 = entityService.findClientByNameAndPass("test", "test");
        assertEquals(Optional.empty(), clientOptional2);

        clearAllTable();
    }
}