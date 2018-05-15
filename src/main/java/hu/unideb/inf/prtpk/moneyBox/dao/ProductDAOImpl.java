package hu.unideb.inf.prtpk.moneyBox.dao;

import hu.unideb.inf.prtpk.moneyBox.dao.api.ProductDAO;
import hu.unideb.inf.prtpk.moneyBox.model.Product;
import hu.unideb.inf.prtpk.moneyBox.utility.EntityManagerFactoryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
     * <pre>LoggerFactory a logoláshoz.</pre>
     */
    private static Logger logger = LoggerFactory.getLogger(GenericDAOImpl.class);

    /**
     * <pre>Konstruktor, mely átadja az osztály típusát.</pre>
     */
    public ProductDAOImpl() {
        super(Product.class);
    }

    @Override
    public Long merge(Product product) {
        logger.info("Merge " + product.getName() + " entity:\n" + product);
        entityManager.getTransaction().begin();
        Product managedEntity = entityManager.merge(product);
        entityManager.getTransaction().commit();
        return managedEntity.getId();
    }
}