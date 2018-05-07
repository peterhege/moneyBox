package hu.unideb.inf.prtpk.moneyBox.service.api;

import hu.unideb.inf.prtpk.moneyBox.model.Client;
import hu.unideb.inf.prtpk.moneyBox.model.Product;
import hu.unideb.inf.prtpk.moneyBox.service.validator.enums.ErrorEnum;

import java.util.List;

/**
 * <pre>Entitások kezelése az üzleti rétegben.</pre>
 */
public interface EntityService {

    /**
     * <pre>Ügyfél felvétele a rendszerbe.</pre>
     *
     * @param client {@link Client} entitás
     * @return Hibák {@link ErrorEnum} listája.
     */
    List<ErrorEnum> createClient(Client client);

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
     * @return Hibák {@link ErrorEnum} listája.
     */
    List<ErrorEnum> updateClient(Client client);

    /**
     * <pre>Termék hozzáadása egy Ügyfélhez, majd felvétel a rendszerbe.</pre>
     *
     * @param client  {@link Client} entitás
     * @param product {@link Product} entitás
     * @return Hibák {@link ErrorEnum} listája.
     */
    List<ErrorEnum> createAndAddProductToClient(Client client, Product product);
}