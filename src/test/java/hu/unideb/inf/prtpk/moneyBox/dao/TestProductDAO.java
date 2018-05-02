package hu.unideb.inf.prtpk.moneyBox.dao;

import hu.unideb.inf.prtpk.moneyBox.dao.api.ProductDAO;
import hu.unideb.inf.prtpk.moneyBox.model.Product;
import hu.unideb.inf.prtpk.moneyBox.utility.EntityManagerFactoryUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestProductDAO {
    private ProductDAO productDAO = new ProductDAOImpl();
    private EntityManager entityManager;

    private Product product = new Product(
            "Huawei Watch okosóra fém tokkal és fémháló szíjjal",
            "https://edigital.hu/okosora/huawei-watch-okosora-fem-tokkal-es-femhalo-szijjal-p408376",
            129990
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
    public void testPersist() {
        productDAO.persist(product);
        List<Product> products = productDAO.getAll();

        assertEquals(1, products.size());

        clearAllTable();
    }

    @Test
    public void testRemove() {
        productDAO.persist(product);
        List<Product> products = productDAO.getAll();
        productDAO.remove(products.get(0));
        products = productDAO.getAll();

        assertEquals(0, products.size());

        clearAllTable();
    }

    @Test
    public void testFindById() {
        productDAO.persist(product);
        List<Product> products = productDAO.getAll();

        Product createdProduct = products.get(0);
        Long id = createdProduct.getId();

        Product foundProduct = productDAO.findById(id).get();

        assertEquals(createdProduct, foundProduct);

        clearAllTable();
    }
}
