package hu.unideb.inf.prtpk.moneyBox.controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.StringProperty;

/**
 * Adatbázisbeli Termékeket leíró osztály.
 */
public class ProductViewModel {

    /**
     * Egy termék automatikusan generált azonosítója.
     */
    private LongProperty id;

    /**
     * Egy termék megnevezése.
     */
    private StringProperty name;

    /**
     * Egy termék url címe (ahol meg lehet vásárolni).
     */
    private StringProperty url;

    /**
     * Egy termék ára.
     */
    private IntegerProperty price;

    /**
     * Egy termékre félretett összeg, alapértelmezetten 0.
     */
    private IntegerProperty savedAmount;

    /**
     * Egy termékhez tartozó felhasználó (entitás).
     * @see ClientViewModel
     */
    private ClientViewModel client;

    /**
     * Üres konstruktor.
     */
    public ProductViewModel() {
    }

    /**
     * <pre>Konstruktor, ha van félretett összeg.</pre>
     *
     * @param name          {@link #name}
     * @param url           {@link #url}
     * @param price         {@link #price}
     * @param savedAmount   {@link #savedAmount}
     */
    public ProductViewModel(StringProperty name, StringProperty url, IntegerProperty price, IntegerProperty savedAmount) {
        this.name = name;
        this.url = url;
        this.price = price;
        this.savedAmount = savedAmount;
    }

    /**
     * @return {@link #id}
     */
    public LongProperty getId() {
        return id;
    }

    /**
     * @param id {@link #id}
     */
    public void setId(LongProperty id) {
        this.id = id;
    }

    /**
     * @return {@link #name}
     */
    public StringProperty getName() {
        return name;
    }

    /**
     * @param name {@link #name}
     */
    public void setName(StringProperty name) {
        this.name = name;
    }

    /**
     * @return {@link #url}
     */
    public StringProperty getUrl() {
        return url;
    }

    /**
     * @param url {@link #url}
     */
    public void setUrl(StringProperty url) {
        this.url = url;
    }

    /**
     * @return {@link #price}
     */
    public IntegerProperty getPrice() {
        return price;
    }

    /**
     * @param price {@link #price}
     */
    public void setPrice(IntegerProperty price) {
        this.price = price;
    }

    /**
     * @return {@link #savedAmount}
     */
    public IntegerProperty getSavedAmount() {
        return savedAmount;
    }

    /**
     * @param savedAmount {@link #savedAmount}
     */
    public void setSavedAmount(IntegerProperty savedAmount) {
        this.savedAmount = savedAmount;
    }

    /**
     * @return {@link #client}
     */
    public ClientViewModel getClient() {
        return client;
    }

    /**
     * @param client {@link #client}
     */
    public void setClient(ClientViewModel client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "ProductViewModel{" +
                "id=" + id +
                ", name=" + name +
                ", url=" + url +
                ", price=" + price +
                ", savedAmount=" + savedAmount +
                '}';
    }
}
