package hu.unideb.inf.prtpk.moneyBox.dao;

import hu.unideb.inf.prtpk.moneyBox.dao.api.ClientDAO;
import hu.unideb.inf.prtpk.moneyBox.model.Product;
import hu.unideb.inf.prtpk.moneyBox.model.Client;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestClientDAO {
    private ClientDAO clientDAO = new ClientDAOImpl();

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

    {
        user.addProduct(product);
    }

    @Test
    public void testPersist() {
        clientDAO.persist(user);
        List<Client> clients = clientDAO.getAllClient();
        clientDAO.remove(clients.get(0));

        assertEquals(1, clients.size());
    }

    @Test
    public void testRemove() {
        clientDAO.persist(user);
        List<Client> clients = clientDAO.getAllClient();
        clientDAO.remove(clients.get(0));
        clients = clientDAO.getAllClient();

        assertEquals(0, clients.size());
    }

    @Test
    public void testFindById() {
        clientDAO.persist(user);
        List<Client> clients = clientDAO.getAllClient();

        Client createdClient = clients.get(0);
        Long id = createdClient.getId();

        Client foundClient = clientDAO.findById(id).get();

        clientDAO.remove(createdClient);
        assertEquals(createdClient, foundClient);
    }
}
