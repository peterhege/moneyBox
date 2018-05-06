package hu.unideb.inf.prtpk.moneyBox.dao.api;

import hu.unideb.inf.prtpk.moneyBox.model.Client;

import java.util.Optional;

/**
 * <pre>Az adatbázisban tárolt felhasználók kezelésére szolgáló osztály.</pre>
 */
public interface ClientDAO extends GenericDAO<Client, Long> {

    /**
     * <pre>Felhasználó keresése felhasználónév alapján.</pre>
     *
     * @param userName Felhasználónév
     * @return {@link Client}
     */
    Optional<Client> findByName(String userName);

    /**
     * <pre>Felhasználó keresése e-mail cím alapján.</pre>
     *
     * @param email Felhasználó e-mail címe
     * @return {@link Client}
     */
    Optional<Client> findByEmail(String email);
}