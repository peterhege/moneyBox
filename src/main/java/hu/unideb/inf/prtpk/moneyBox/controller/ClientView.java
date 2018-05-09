package hu.unideb.inf.prtpk.moneyBox.controller;

import hu.unideb.inf.prtpk.moneyBox.model.Product;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * JSF Felhasználókat leíró osztály.
 */
@ManagedBean
@SessionScoped
public class ClientView {

    /**
     * Azonosító.
     */
    private Long id;

    /**
     * Egy felhasználó fehasználói neve.
     */
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
    private List<Product> products;

    /**
     * <pre>Üres konstruktor, a termékeknek üres lista létrehozása.</pre>
     */
    public ClientView() {
        this.products = new ArrayList<>();
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
    public String getClientName() {
        return userName;
    }

    /**
     * @param userName {@link #userName}
     */
    public void setClientName(String userName) {
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
        return "Client{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", products=" + products +
                '}';
    }
}
