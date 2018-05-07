package hu.unideb.inf.prtpk.moneyBox.service.validator;

import hu.unideb.inf.prtpk.moneyBox.dao.api.ClientDAO;
import hu.unideb.inf.prtpk.moneyBox.dao.api.ProductDAO;
import hu.unideb.inf.prtpk.moneyBox.model.Product;
import hu.unideb.inf.prtpk.moneyBox.service.validator.api.Validator;
import hu.unideb.inf.prtpk.moneyBox.service.validator.enums.*;
import hu.unideb.inf.prtpk.moneyBox.utility.EntityManagerFactoryUtil;
import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductValidator implements Validator<Product> {
    /**
     * Termékkezelő DAO.
     */
    private ProductDAO productDAO;

    /**
     * Ügyfélkezelő DAO.
     */
    private ClientDAO clientDAO;

    /**
     * <pre>LoggerFactory a logoláshoz.</pre>
     */
    private static Logger logger = LoggerFactory.getLogger(EntityManagerFactoryUtil.class);

    /**
     * Hibák listája.
     */
    private List<ErrorEnum> errorList;

    /**
     * Vizsgálandó {@link Product}.
     */
    private Product product;

    /**
     * <pre>Konstruktor.</pre>
     *
     * @param productDAO {@link ProductDAO}
     */
    public ProductValidator(ProductDAO productDAO, ClientDAO clientDAO) {
        this.productDAO = productDAO;
        this.clientDAO = clientDAO;
    }

    /**
     * <pre>Megnevezés ellenőrzése.</pre>
     * <ul>
     * <li>A megnevezés legalább három karakter kell hogy legyen.</li>
     * </ul>
     */
    private void nameIsValid() {
        logger.info("Validate Product name");
        if (product.getName().length() <= 3) {
            errorList.add(ErrorEnum.SHORT_NAME);
            logger.warn("Product name is too short: " + product.getName().length());
        }
    }

    /**
     * <pre>
     *     A termék webcímének ellenőrzése.
     *     Mivel nem kötelező megadni, csak akkor elenőrizzük, ha meg van adva.
     * </pre>
     *
     * @see UrlValidator
     */
    private void urlIsValid() {
        logger.info("Validate Product url");
        String url = product.getUrl();
        if (!url.equals("") && !UrlValidator.getInstance().isValid(url)) {
            errorList.add(ErrorEnum.INVALID_URL);
            logger.warn("Product url [" + url + "] is invalid!");
        }
    }

    /**
     * <pre>Megadott összeg ellenőrzése.</pre>
     * <ul>
     * <li>Az összeget kötelező megadni</li>
     * <li>Az összegnek pozitívnak kell lennie</li>
     * </ul>
     */
    private void priceIsValid() {
        logger.info("Validate Product price");
        int price = product.getPrice();
        if (price == 0) {
            errorList.add(ErrorEnum.NOT_EXIST_PRICE);
            logger.warn("Product price isn't exist!");
        } else if (price < 0) {
            errorList.add(ErrorEnum.INVALID_PRICE);
            logger.warn("Product price [" + price + "] is invalid!");
        }
    }

    /**
     * <pre>Félretett összeg ellenőrzése.</pre>
     * <ul>
     * <li>A félretett összeg nem lehet kisebb nullánál</li>
     * </ul>
     */
    private void savedAmountIsValid() {
        logger.info("Validate Saved Amount");
        int savedAmount = product.getSavedAmount();
        if (savedAmount < 0) {
            errorList.add(ErrorEnum.INVALID_PRICE);
            logger.warn("Product saved amount [" + savedAmount + "] is invalid!");
        }
    }

    /**
     * <pre>Létezik a termékhez megadott felhasználó?</pre>
     */
    private void clientIsExist() {
        logger.info("Validate Client is exist");
        if (!clientDAO.findById(product.getClient().getId()).isPresent()) {
            errorList.add(ErrorEnum.NOT_EXIST_ID);
            logger.warn("Product Client not exist!");
        }
    }

    /**
     * <pre>Termék azonosító keresése. Ha nem található, hiba.</pre>
     */
    private void productIdIsNotExist() {
        logger.info("Validate Product ID is exist");
        if (!productDAO.findById(product.getId()).isPresent()) {
            errorList.add(ErrorEnum.NOT_EXIST_ID);
            logger.warn("ID not found!");
        }
    }

    /**
     * <pre>A mindenképp ellenőrzendő metódusok futtatása.</pre>
     */
    private void defaultValidate() {
        nameIsValid();
        priceIsValid();
        savedAmountIsValid();
        urlIsValid();
        clientIsExist();
    }

    @Override
    public List<ErrorEnum> validate(Product product, ValidateType type) {
        this.errorList = new ArrayList<>();
        this.product = product;

        switch (type) {
            case CREATE:
                defaultValidate();
                break;
            case UPDATE:
                defaultValidate();
                productIdIsNotExist();
                break;
        }

        return errorList;
    }
}