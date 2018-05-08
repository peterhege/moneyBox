package hu.unideb.inf.prtpk.moneyBox.controller;

import javafx.beans.property.LongProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Adatbázisbeli Felhasználókat leíró osztály.
 */
public class ClientViewModel {

    /**
     * Egy felhasználó automatikusan generált azonosítója.
     */
    private LongProperty id;

    /**
     * Egy felhasználó fehasználói neve.
     */
    @Column(unique = true, nullable = false)
    private StringProperty userName;

    /**
     * Egy felhasználó jelszava.
     */
    private StringProperty password;

    /**
     * Egy felhasználó email címe.
     */
    private StringProperty email;

    /**
     * Egy felhasználó termékeinek listája.
     *
     * @see ProductViewModel
     */
    private List<ProductViewModel> products;

    /**
     * <pre>Üres konstruktor, a termékeknek üres lista létrehozása.</pre>
     */
    public ClientViewModel() {
        this.products = new ArrayList<>();
    }

    /**
     * <pre>Konstruktor, a termékeknek üres lista létrehozása.</pre>
     *
     * @param userName {@link #userName}
     * @param password {@link #password}
     * @param email    {@link #email}
     */
    public ClientViewModel(StringProperty userName, StringProperty password, StringProperty email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.products = new ArrayList<>();
    }

    /**
     * <pre>Hozzáadás a felhasználó termékeinek listájához.</pre>
     *
     * @param product {@link ProductViewModel}
     */
    public void addProduct(ProductViewModel product) {
        this.products.add(product);
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
     * @return {@link #userName}
     */
    public StringProperty getClientViewModelName() {
        return userName;
    }

    /**
     * @param userName {@link #userName}
     */
    public void setClientViewModelName(StringProperty userName) {
        this.userName = userName;
    }

    /**
     * @return {@link #password}
     */
    public StringProperty getPassword() {
        return password;
    }

    /**
     * @param password {@link #password}
     */
    public void setPassword(StringProperty password) {
        this.password = password;
    }

    /**
     * @return {@link #email}
     */
    public StringProperty getEmail() {
        return email;
    }

    /**
     * @param email {@link #email}
     */
    public void setEmail(StringProperty email) {
        this.email = email;
    }

    /**
     * @return {@link #products}
     */
    public List<ProductViewModel> getProduct() {
        return products;
    }

    /**
     * @param products {@link #products}
     */
    public void setProduct(List<ProductViewModel> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ClientViewModel{" +
                "id=" + id +
                ", userName=" + userName +
                ", password=" + password +
                ", email=" + email +
                ", products=" + products +
                '}';
    }
}
