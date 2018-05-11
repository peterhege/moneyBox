package hu.unideb.inf.prtpk.moneyBox.controller;

import hu.unideb.inf.prtpk.moneyBox.model.Client;
import hu.unideb.inf.prtpk.moneyBox.model.Product;
import hu.unideb.inf.prtpk.moneyBox.service.EntityServiceImpl;
import hu.unideb.inf.prtpk.moneyBox.service.api.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.Map;

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

    private String url;

    private String name;

    private int price;

    private int savedAmount;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSavedAmount() {
        return savedAmount;
    }

    public void setSavedAmount(int savedAmount) {
        this.savedAmount = savedAmount;
    }

    public String loadFromUrl() {
        logger.debug("Load from url.");
        return "productUrlChecked";
    }

    public String createProduct() {
        logger.debug("Create Product");
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        logger.debug("Get client session.");
        ClientController client = (ClientController) session.get("clientController");
        Product product = new Product(name, url, price);
        entityService.createAndAddProductToClient(client.getClient(), product);
        return null;
    }
}
