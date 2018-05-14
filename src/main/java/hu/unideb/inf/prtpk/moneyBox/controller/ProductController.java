package hu.unideb.inf.prtpk.moneyBox.controller;

import hu.unideb.inf.prtpk.moneyBox.model.Product;
import hu.unideb.inf.prtpk.moneyBox.service.EntityServiceImpl;
import hu.unideb.inf.prtpk.moneyBox.service.api.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.Map;

/**
 * Termékek kezelése.
 */
@ManagedBean
public class ProductController {
    /**
     * <pre>LoggerFactory a logoláshoz.</pre>
     */
    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    /**
     * Entitások kezelése.
     */
    private EntityService entityService = new EntityServiceImpl();

    /**
     * Termék url címe.
     */
    private String url;

    /**
     * Termék neve.
     */
    private String name;

    /**
     * Termék vételára.
     */
    private int price;

    /**
     * Termékre megspórolt összeg.
     */
    private int savedAmount;

    /**
     * @return {@link #url}
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url {@link #url}
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * @param name {@link #name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return {@link #price}
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price {@link #price}
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return {@link #savedAmount}
     */
    public int getSavedAmount() {
        return savedAmount;
    }

    /**
     * @param savedAmount {@link #savedAmount}
     */
    public void setSavedAmount(int savedAmount) {
        this.savedAmount = savedAmount;
    }

    /**
     * <pre>Adatok betöltése a megadott URL cím alapján.</pre>
     * @return Oldal címe.
     */
    public String loadFromUrl() {
        logger.debug("Load from url.");
        return "productUrlChecked";
    }

    /**
     * <pre>Termék mentése.</pre>
     * @return Oldal címe.
     */
    public String createProduct() {
        logger.debug("Create Product");
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        logger.debug("Get client session.");
        ClientController client = (ClientController) session.get("clientController");
        Product product = new Product(name, url, price);
        entityService.createAndAddProductToClient(client.getClient(), product);
        return "products";
    }
}
