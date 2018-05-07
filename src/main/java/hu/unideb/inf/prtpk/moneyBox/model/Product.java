package hu.unideb.inf.prtpk.moneyBox.model;

import javax.persistence.*;

/**
 * Adatbázisbeli Termékeket leíró osztály.
 */
@Entity
public class Product {

    /**
     * Egy termék automatikusan generált azonosítója.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Client client;

    /**
     * Üres konstruktor.
     */
    public Product() {
    }

    /**
     * <pre>Konstuktor, ha még nincs félretett összeg.</pre>
     *
     * @param name  {@link #name}
     * @param url   {@link #url}
     * @param price {@link #price}
     */
    public Product(String name, String url, int price) {
        this.name = name;
        this.url = url;
        this.price = price;
        this.savedAmount = 0;
    }

    /**
     * <pre>Konstruktor, ha van félretett összeg.</pre>
     *
     * @param name          {@link Product#name}
     * @param url           {@link Product#url}
     * @param price         {@link Product#price}
     * @param savedAmount   {@link Product#savedAmount}
     */
    public Product(String name, String url, int price, int savedAmount) {
        this.name = name;
        this.url = url;
        this.price = price;
        this.savedAmount = savedAmount;
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
    public Client getClient() {
        return client;
    }

    /**
     * @param client {@link #client}
     */
    public void setClient(Client client) {
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
