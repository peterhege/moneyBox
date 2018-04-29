package hu.unideb.inf.prtpk.moneyBox.dao.api;


import hu.unideb.inf.prtpk.moneyBox.model.Client;

import java.util.List;

/**
 * <pre>Az adatbázisban tárolt felhasználók kezelésére szolgáló osztály.</pre>
 */
public interface ClientDAO extends GenericDAO<Client, Long> {
    /**
     * <pre>Visszaadja az összes felhasználót az adatbázisból.</pre>
     * <code class="example-code">
     * ClientDAO dao = new ClientDAOImpl();<br>
     * dao.getAllClient();
     * </code>
     *
     * @return Termékek listája
     */
    List<Client> getAllClient();
}