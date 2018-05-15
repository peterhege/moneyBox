package hu.unideb.inf.prtpk.moneyBox.dao.api;

import hu.unideb.inf.prtpk.moneyBox.model.Product;

/**
 * <pre>Az adatbázisban tárolt termékek kezelésére szolgáló osztály.</pre>
 */
public interface ProductDAO extends GenericDAO<Product, Long> {
    /**
     * <pre>Az adatbázisban lévő termék adatainak frissítése.</pre>
     *
     * @param product {@link Product} entitás.
     * @return A létrejött termék adatbázisbeli azonosítója.
     */
    Long merge(Product product);
}
