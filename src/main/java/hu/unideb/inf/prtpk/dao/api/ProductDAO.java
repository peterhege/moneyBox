package hu.unideb.inf.prtpk.dao.api;

import hu.unideb.inf.prtpk.model.Product;

import java.util.List;

/**
 * Termékek kezelése | Manage products
 */
public interface ProductDAO extends GenericDAO<Product, Long> {
    /**
     * Összes termék listába rendezése | All products into the list
     * @return Termékek listája | List of products
     */
    List<Product> getAllProduct();
}
