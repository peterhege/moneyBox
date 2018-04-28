package hu.unideb.inf.prtpk.moneyBox.dao.api;

import hu.unideb.inf.prtpk.moneyBox.model.Product;

import java.util.List;

/**
 * <pre>Az adatbázisban tárolt termékek kezelésére szolgáló osztály.</pre>
 */
public interface ProductDAO extends GenericDAO<Product, Long> {
    /**
     * <pre>Visszaadja az összes terméket az adatbázisból.</pre>
     * <code class="example-code">
     *     ProductDAO dao = new ProductDAOImpl();<br>
     *     dao.getAllProduct();
     * </code>
     * @return Termékek listája
     */
    List<Product> getAllProduct();
}
