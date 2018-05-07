package hu.unideb.inf.prtpk.moneyBox.dao.api;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * <pre>A különböző DAO-knál használt közös metódusok gyűjteménye.</pre>
 *
 * @param <T>  Egy entitás típusa, pl.: <code>Product</code>
 * @param <ID> A <code>T</code> azonosítójának típusa, pl.: <code>Long</code>
 */
public interface GenericDAO<T, ID> {

    /**
     * <pre>Egy entitás lepéldányosítása az adatbázisba.</pre>
     * <code class="example-code">
     * Product product = new Product(...);<br>
     * ProductDAO dao = new ProductDAOImpl();<br>
     * dao.persist(product);
     * </code>
     *
     * @param entity Az entitás példánya
     */
    void persist(T entity);

    /**
     * <pre>Adott entitás adatbázisbeli példányának keresése azonosító alapján.</pre>
     * <code class="example-code">
     * Long id = ...;<br>
     * ProductDAO dao = new ProductDAOImpl();<br>
     * Optional&lt;Product&gt; product = dao.findById(id);<br>
     * product.ifPresent(p-&gt; System.out.println(p.getName()));
     * </code>
     *
     * @param id Az entitás azonosítója, pl.: 1
     * @return Entitásnak megfelelő {@link Optional}
     */
    Optional<T> findById(ID id);

    /**
     * <pre>Adott entitás adatbázisbeli példányának keresése a megadott paraméterek alapján.</pre>
     *
     * @param entityParamName Mely paraméter alapján keressünk
     * @param entityParam     A paraméter értéke, ami alapján keressünk
     * @return Entitásnak megfelelő {@link Optional}
     */
    Optional<T> findBy(String entityParamName, String entityParam);

    /**
     * <pre>Entitás eltávolítása az adatbázisból.</pre>
     * <code class="example-code">
     * Long id = ...;<br>
     * ProductDAO dao = new ProductDAOImpl();<br>
     * Optional&lt;Product&gt; product = dao.findById(id);<br>
     * product.ifPresent(dao::remove);
     * </code>
     *
     * @param entity Az entitás példánya
     */
    void remove(T entity);

    /**
     * <pre>Adatbázisbeli entitás összefésülése a módosított példányával.</pre>
     *
     * @param entity Entitás példánya
     */
    void merge(T entity);

    /**
     * <pre>Az összes adatbázisbeli T entitás listázása.</pre>
     * <code class="example-code">
     * Long id = ...;<br>
     * ProductDAO dao = new ProductDAOImpl();<br>
     * dao.getAll();
     * </code>
     *
     * @return T entitások listája.
     */
    List<T> getAll();
}