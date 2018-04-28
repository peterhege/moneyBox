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
 * ProductDAO implementálása | Implemens ProductDAO
 * @see ProductDAO
 */
public class ProductDAOImpl implements ProductDAO{

    private EntityManager entityManager;

    /**
     * Konstruktor | Constructor
     * EntityManager készítése | Make EntityManager
     */
    public ProductDAOImpl() {
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryUtil.getInstance().getEntityManagerFactory();
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<Product> getAllProduct() {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM hu.unideb.inf.prtpk.moneyBox.model.Product p", Product.class);
        return query.getResultList();
    }

    @Override
    public void persist(Product entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.of(entityManager.find(Product.class, id));
    }

    @Override
    public void remove(Product entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }
}
