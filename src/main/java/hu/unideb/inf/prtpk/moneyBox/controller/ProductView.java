package hu.unideb.inf.prtpk.moneyBox.controller;

import hu.unideb.inf.prtpk.moneyBox.model.Client;

import javax.faces.bean.ManagedBean;

/**
 * JSF Termékeket leíró osztály.
 */
@ManagedBean
public class ProductView {

    /**
     * Egy termék automatikusan generált azonosítója.
     */
    private Long id;

    /**
     * Egy termék megnevezése.
     */
    private String name;

    /**
     * Egy termék url címe (ahol meg lehet vásárolni).
     */
    private String url;

    /**
     * Egy termék ára.
     */
    private int price = 0;

    /**
     * Egy termékre félretett összeg, alapértelmezetten 0.
     */
    private int savedAmount = 0;

    /**
     * Egy termékhez tartozó felhasználó (entitás).
     * @see Client
     */
    private ClientView client;

    /**
     * Üres konstruktor.
     */
    public ProductView() {
    }

    /**
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id {@link #id}
     */
    public void setId(Long id) {
        this.id = id;
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
     * @return {@link #client}
     */
    public ClientView getClient() {
        return client;
    }

    /**
     * @param client {@link #client}
     */
    public void setClient(ClientView client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", price=" + price +
                ", savedAmount=" + savedAmount +
                '}';
    }
}
