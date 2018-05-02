package hu.unideb.inf.prtpk.moneyBox.dao;

import hu.unideb.inf.prtpk.moneyBox.dao.api.ClientDAO;
import hu.unideb.inf.prtpk.moneyBox.model.Client;

/**
 * <pre>ClientDAO implementálása.</pre>
 *
 * @see ClientDAO
 */
public class ClientDAOImpl extends GenericDAOImpl<Client, Long> implements ClientDAO {

    /**
     * <pre>Konstruktor, mely átadja az osztály típusát.</pre>
     */
    public ClientDAOImpl() {
        super(Client.class);
    }
}