package hu.unideb.inf.prtpk.moneyBox.dao;

import hu.unideb.inf.prtpk.moneyBox.dao.api.ProductDAO;
import hu.unideb.inf.prtpk.moneyBox.model.Product;
import hu.unideb.inf.prtpk.moneyBox.utility.EntityManagerFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * <pre>ProductDAO implementálása.</pre>
 *
 * @see ProductDAO
 */
public class ProductDAOImpl extends GenericDAOImpl<Product, Long> implements ProductDAO {
    /**
     * <pre>Konstruktor, mely átadja az osztály típusát.</pre>
     */
    public ProductDAOImpl() {
        super(Product.class);
    }
}