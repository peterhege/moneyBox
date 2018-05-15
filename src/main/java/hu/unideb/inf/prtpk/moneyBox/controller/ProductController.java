package hu.unideb.inf.prtpk.moneyBox.controller;

import hu.unideb.inf.prtpk.moneyBox.Error.Error;
import hu.unideb.inf.prtpk.moneyBox.Error.ErrorField;
import hu.unideb.inf.prtpk.moneyBox.model.Client;
import hu.unideb.inf.prtpk.moneyBox.model.Product;
import hu.unideb.inf.prtpk.moneyBox.service.EntityServiceImpl;
import hu.unideb.inf.prtpk.moneyBox.service.api.EntityService;
import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Termékek kezelése.
 */
@ManagedBean
public class ProductController implements Serializable {
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
     * URL címmel kapcsolatos hibák listája.
     */
    private List<String> urlErrors = new ArrayList<>();

    /**
     * Termék neve.
     */
    private String name;

    /**
     * Termék nevével kapcsolatos hibák listája.
     */
    private List<String> nameErrors = new ArrayList<>();

    /**
     * Termék vételára.
     */
    private int price;

    /**
     * Termék árával kapcsolatos hibák listája.
     */
    private List<String> priceErrros = new ArrayList<>();

    /**
     * Termékre megspórolt összeg.
     */
    private int savedAmount;

    /**
     * Termékre megspórolt összeggel kapcsolatos hibák listája.
     */
    private List<String> savedAmountErrors = new ArrayList<>();

    /**
     * Helyes-e az url cím.
     */
    private boolean urlIsChecked;

    /**
     * Egy {@link Product} entitás példánya.
     */
    private Product product;

    /**
     * A spróolt összeg növekménye.
     */
    private int inc;

    /**
     * Termékkel kapcsolatos hibák listája.
     */
    private List<String> productErrors = new ArrayList<>();

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
     * @return {@link #urlIsChecked}
     */
    public boolean isUrlIsChecked() {
        return urlIsChecked;
    }

    /**
     * @param urlIsChecked {@link #urlIsChecked}
     */
    public void setUrlIsChecked(boolean urlIsChecked) {
        this.urlIsChecked = urlIsChecked;
    }

    /**
     * @return {@link #inc}
     */
    public int getInc() {
        return inc;
    }

    /**
     * @param inc {@link #inc}
     */
    public void setInc(int inc) {
        this.inc = inc;
    }

    /**
     * @return {@link #urlErrors}
     */
    public List<String> getUrlErrors() {
        return urlErrors;
    }

    /**
     * @param urlErrors {@link #urlErrors}
     */
    public void setUrlErrors(List<String> urlErrors) {
        this.urlErrors = urlErrors;
    }

    /**
     * @return {@link #nameErrors}
     */
    public List<String> getNameErrors() {
        return nameErrors;
    }

    /**
     * @param nameErrors {@link #nameErrors}
     */
    public void setNameErrors(List<String> nameErrors) {
        this.nameErrors = nameErrors;
    }

    /**
     * @return {@link #productErrors}
     */
    public List<String> getPriceErrros() {
        return priceErrros;
    }

    /**
     * @param priceErrros {@link #priceErrros}
     */
    public void setPriceErrros(List<String> priceErrros) {
        this.priceErrros = priceErrros;
    }

    /**
     * @return {@link #savedAmountErrors}
     */
    public List<String> getSavedAmountErrors() {
        return savedAmountErrors;
    }

    /**
     * @param savedAmountErrors {@link #savedAmountErrors}
     */
    public void setSavedAmountErrors(List<String> savedAmountErrors) {
        this.savedAmountErrors = savedAmountErrors;
    }

    /**
     * @return {@link #productErrors}
     */
    public List<String> getProductErrors() {
        return productErrors;
    }

    /**
     * @param productErrors {@link #productErrors}
     */
    public void setProductErrors(List<String> productErrors) {
        this.productErrors = productErrors;
    }

    /**
     * <pre>Adatok betöltése a megadott URL cím alapján.</pre>
     *
     * @return Oldal címe.
     */
    public String loadFromUrl() {
        logger.debug("Load from url.");
        if (UrlValidator.getInstance().isValid(url)) urlIsChecked = true;
        else {
            urlIsChecked = false;
            urlErrors.add("Hibás url cím!");
        }
        return null;
    }

    /**
     * <pre>Termék mentése a Bean értékei lapján.</pre>
     *
     * @return Oldal címe.
     */
    public String createProduct() {
        logger.debug("Create Product");
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        logger.debug("Get client session.");
        ClientController client = (ClientController) session.get("clientController");
        Product product = new Product(name, url, price);
        List<Error> errorList = entityService.createAndAddProductToClient(client.getClient(), product);
        urlIsChecked = errorList.size() != 0;

        if (errorList.size() > 0) {
            this.urlErrors = errorList.stream().filter(e -> e.getField().equals(ErrorField.URL)).map(Error::getMessage).collect(Collectors.toList());
            this.nameErrors = errorList.stream().filter(e -> e.getField().equals(ErrorField.PRODUCT_NAME)).map(Error::getMessage).collect(Collectors.toList());
            this.priceErrros = errorList.stream().filter(e -> e.getField().equals(ErrorField.PRICE)).map(Error::getMessage).collect(Collectors.toList());
            this.savedAmountErrors = errorList.stream().filter(e -> e.getField().equals(ErrorField.SAVED_AMOUNT)).map(Error::getMessage).collect(Collectors.toList());
        }

        return null;
    }

    /**
     * <pre>A félretett összeg növelése.</pre>
     *
     * @param product {@link #product}
     * @return Oldal.
     */
    public String incSavedAmount() {
        logger.debug("Product: " + product + ", " + inc);
        product.setSavedAmount(product.getSavedAmount() + inc);
        entityService.updateProduct(product);
        return null;
    }

    /**
     * @return {@link #product}
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product {@link #product}
     */
    public void setProduct(Product product) {
        this.product = product;
    }
}
