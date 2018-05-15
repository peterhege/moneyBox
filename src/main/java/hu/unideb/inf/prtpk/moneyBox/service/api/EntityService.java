package hu.unideb.inf.prtpk.moneyBox.service.api;

import hu.unideb.inf.prtpk.moneyBox.model.Client;
import hu.unideb.inf.prtpk.moneyBox.model.Product;
import hu.unideb.inf.prtpk.moneyBox.Error.Error;

import java.util.List;
import java.util.Optional;

/**
 * <pre>Entitások kezelése az üzleti rétegben.</pre>
 */
public interface EntityService {

    /**
     * <pre>Ügyfél felvétele a rendszerbe.</pre>
     *
     * @param client {@link Client} entitás
     * @return Hibák {@link Error} listája.
     */
    List<Error> createClient(Client client);

    /**
     * <pre>Ügyfél törlése a rendszerből.</pre>
     *
     * @param id Az ügyfél azonosítója
     */
    void removeClient(Long id);

    /**
     * <pre>Ügyfél adatainak frissítése.</pre>
     *
     * @param client Friss adatokkal rendelkező {@link Client}
     * @return Hibák {@link Error} listája.
     */
    List<Error> updateClient(Client client);

    /**
     * <pre>Termék hozzáadása egy Ügyfélhez, majd felvétel a rendszerbe.</pre>
     *
     * @param client  {@link Client} entitás
     * @param product {@link Product} entitás
     * @return Hibák {@link Error} listája.
     */
    List<Error> createAndAddProductToClient(Client client, Product product);

    /**
     * <pre>Felhasználó keresése Felhasználónév, Jelszó páros alapján.</pre>
     *
     * @param userName Felhasználónév
     * @param password Jelszó
     * @return {@link Client} {@link Optional}
     */
    Optional<Client> findClientByNameAndPass(String userName, String password);


    /**
     * <pre>Termék frissítése.</pre>
     * @param product {@link Product} entitás
     * @return Hibák listája.
     */
    List<Error> updateProduct(Product product);
}
