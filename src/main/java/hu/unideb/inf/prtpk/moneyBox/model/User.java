package hu.unideb.inf.prtpk.moneyBox.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Adatbázisbeli Felhasználókat leíró osztály.
 */
@Entity
public class User {

    /**
     * Egy felhasználó automatikusan generált azonosítója.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Egy felhasználó fehasználói neve.
     */
    @Column(unique = true, nullable = false)
    private String userName;

    /**
     * Egy felhasználó jelszava.
     */
    private String password;

    /**
     * Egy felhasználó email címe.
     */
    private String email;

    /**
     * Egy felhasználó termékeinek listája.
     *
     * @see Product
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Product> products;

    /**
     * <pre>Üres konstruktor, a termékeknek üres lista létrehozása.</pre>
     */
    public User() {
        this.products = new ArrayList<Product>();
    }

    /**
     * <pre>Konstruktor, a termékeknek üres lista létrehozása.</pre>
     *
     * @param userName {@link #userName}
     * @param password {@link #password}
     * @param email    {@link #email}
     */
    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.products = new ArrayList<Product>();
    }

    /**
     * <pre>Hozzáadás a felhasználó termékeinek listájához.</pre>
     *
     * @param product {@link Product}
     */
    public void addProduct(Product product) {
        this.products.add(product);
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
     * @return {@link #userName}
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName {@link #userName}
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return {@link #password}
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password {@link #password}
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return {@link #email}
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email {@link #email}
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return {@link #products}
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * @param products {@link #products}
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", products=" + products +
                '}';
    }
}
