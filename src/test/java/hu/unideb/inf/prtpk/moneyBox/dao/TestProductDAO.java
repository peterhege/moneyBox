package hu.unideb.inf.prtpk.moneyBox.dao;

import hu.unideb.inf.prtpk.moneyBox.dao.api.ProductDAO;
import hu.unideb.inf.prtpk.moneyBox.model.Product;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestProductDAO {
    private ProductDAO productDAO = new ProductDAOImpl();

    private Product product = new Product(
            "Huawei Watch okosóra fém tokkal és fémháló szíjjal",
            "https://edigital.hu/okosora/huawei-watch-okosora-fem-tokkal-es-femhalo-szijjal-p408376",
            129990
    );

    @Test
    public void testPersist() {
        productDAO.persist(product);
        List<Product> products = productDAO.getAll();
        productDAO.remove(products.get(0));

        assertEquals(1, products.size());
    }

    @Test
    public void testRemove() {
        productDAO.persist(product);
        List<Product> products = productDAO.getAll();
        productDAO.remove(products.get(0));
        products = productDAO.getAll();

        assertEquals(0, products.size());
    }

    @Test
    public void testFindById(){
        productDAO.persist(product);
        List<Product> products = productDAO.getAll();

        Product createdProduct = products.get(0);
        Long id = createdProduct.getId();

        Product foundProduct = productDAO.findById(id).get();

        productDAO.remove(createdProduct);
        assertEquals(createdProduct, foundProduct);
    }
}
