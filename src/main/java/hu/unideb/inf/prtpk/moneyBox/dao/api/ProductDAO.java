package hu.unideb.inf.prtpk.moneyBox.dao.api;

import hu.unideb.inf.prtpk.moneyBox.model.Product;

import java.util.List;

/**
 * Termékek kezelése | Manage Products
 */
public interface ProductDAO extends GenericDAO<Product, Long> {
    /**
     * @return Összes termék az adatbázisból | All Product from Database
     */
    List<Product> getAllProduct();
}
