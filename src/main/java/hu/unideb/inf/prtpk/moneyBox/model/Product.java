package hu.unideb.inf.prtpk.moneyBox.model;

import javax.persistence.*;

/**
 * Termék Entity | Product Entity
 */
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String url;

    private int price;

    private int savedAmount;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

    public Product() {
        this.savedAmount = 0;
    }

    public Product(String name, String url, int price) {
        this.name = name;
        this.url = url;
        this.price = price;
        this.savedAmount = 0;
    }

    public Product(String name, String url, int price, int savedAmount) {
        this.name = name;
        this.url = url;
        this.price = price;
        this.savedAmount = savedAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
