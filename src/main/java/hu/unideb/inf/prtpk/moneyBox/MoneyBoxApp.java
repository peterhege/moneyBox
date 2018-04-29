package hu.unideb.inf.prtpk.moneyBox;

import hu.unideb.inf.prtpk.moneyBox.dao.ClientDAOImpl;
import hu.unideb.inf.prtpk.moneyBox.dao.ProductDAOImpl;
import hu.unideb.inf.prtpk.moneyBox.dao.api.ClientDAO;
import hu.unideb.inf.prtpk.moneyBox.dao.api.ProductDAO;
import hu.unideb.inf.prtpk.moneyBox.model.Product;

import java.util.Optional;

/**
 * <pre>A fő osztály.</pre>
 */
public class MoneyBoxApp {
    /**
     * <pre>Automatikusan hívandó metódus.</pre>
     * @param args STDIN paraméter tömb
     */
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAOImpl();
        Product product = new Product(
                "Huawei Watch okosóra fém tokkal és fémháló szíjjal",
                "https://edigital.hu/okosora/huawei-watch-okosora-fem-tokkal-es-femhalo-szijjal-p408376",
                129990
        );

        ClientDAO clientDAO = new ClientDAOImpl();

        productDAO.persist(product);
    }
}
