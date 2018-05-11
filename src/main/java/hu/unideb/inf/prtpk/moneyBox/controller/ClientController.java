package hu.unideb.inf.prtpk.moneyBox.controller;

import hu.unideb.inf.prtpk.moneyBox.Error.ErrorField;
import hu.unideb.inf.prtpk.moneyBox.model.Client;
import hu.unideb.inf.prtpk.moneyBox.service.EntityServiceImpl;
import hu.unideb.inf.prtpk.moneyBox.service.api.EntityService;
import hu.unideb.inf.prtpk.moneyBox.Error.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <pre>Bejelenetkezést végző osztály.</pre>
 */
@ManagedBean
@SessionScoped
public class ClientController {

    /**
     * <pre>LoggerFactory a logoláshoz.</pre>
     */
    private static Logger logger = LoggerFactory.getLogger(ClientController.class);

    /**
     * Felhasználónév.
     */
    private String userName;

    /**
     * Felhasználónév hibái.
     */
    private List<String> userNameErrors = new ArrayList<>();

    /**
     * Felhasználó e-mail címe.
     */
    private String email;

    /**
     * e-mail cím hibái.
     */
    private List<String> emailErrors = new ArrayList<>();

    /**
     * Jelszó.
     */
    private String password;

    /**
     * Jelszó hibái.
     */
    private List<String> passwordErrors = new ArrayList<>();

    /**
     * Felhasználó entitása.
     */
    private Client client;

    /**
     * Bejelentkezéskor fellépő hibák listája.
     */
    private List<String> loginErrors = new ArrayList<>();

    /**
     * Regisztrációkor fellépő hibák listája.
     */
    private List<String> regErrors = new ArrayList<>();

    /**
     * Entitások kezelése.
     */
    private EntityService entityService = new EntityServiceImpl();

    /**
     * Hibalisták ürítése.
     */
    private void clearErrrors() {
        loginErrors = new ArrayList<>();
        regErrors = new ArrayList<>();
        userNameErrors = new ArrayList<>();
        passwordErrors = new ArrayList<>();
        emailErrors = new ArrayList<>();
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

    /**
     * @return {@link #userNameErrors}
     */
    public List<String> getUserNameErrors() {
        return userNameErrors;
    }

    /**
     * @param userNameErrors {@link #userNameErrors}
     */
    public void setUserNameErrors(List<String> userNameErrors) {
        this.userNameErrors = userNameErrors;
    }

    /**
     * @return {@link #emailErrors}
     */
    public List<String> getEmailErrors() {
        return emailErrors;
    }

    /**
     * @param emailErrors {@link #emailErrors}
     */
    public void setEmailErrors(List<String> emailErrors) {
        this.emailErrors = emailErrors;
    }

    /**
     * @return {@link #passwordErrors}
     */
    public List<String> getPasswordErrros() {
        return passwordErrors;
    }

    /**
     * @param passwordErrors {@link #passwordErrors}
     */
    public void setPasswordErrros(List<String> passwordErrors) {
        this.passwordErrors = passwordErrors;
    }

    /**
     * @return {@link #loginErrors}
     */
    public List<String> getLoginErrors() {
        return loginErrors;
    }

    /**
     * @param loginErrors {@link #loginErrors}
     */
    public void setLoginErrors(List<String> loginErrors) {
        this.loginErrors = loginErrors;
    }

    /**
     * @return {@link #regErrors}
     */
    public List<String> getRegErrors() {
        return regErrors;
    }

    /**
     * @param regErrors {@link #regErrors}
     */
    public void setRegErrors(List<String> regErrors) {
        this.regErrors = regErrors;
    }

    /**
     * <pre>A felhasználó beléptetése a rendszerbe.</pre>
     *
     * @return Ide navigál az oldal.
     */
    public String login() {
        clearErrrors();
        Optional<Client> optionalClient = entityService.findClientByNameAndPass(userName, password);
        if (optionalClient.isPresent()) {
            logger.debug("Find the user!");
            this.client = optionalClient.get();
        } else loginErrors.add("Hibás felhasználónév vagy jelszó.");
        return null;
    }

    /**
     * <pre>A felhasználó kiléptetése a rendszerből.</pre>
     *
     * @return Ide navigál az oldal.
     */
    public String logout() {
        client = null;
        return null;
    }

    /**
     * <pre>Felhasználó rigsztrálása a rendszerbe.</pre>
     *
     * @return Ide navigál az oldal.
     */
    public String registration() {
        clearErrrors();
        Client client = new Client(userName, password, email);
        List<Error> errorList = entityService.createClient(client);
        if (errorList.size() == 0) this.client = client;
        else {
            logger.debug(errorList.toString());
            this.userNameErrors = errorList.stream().filter(e -> e.getField().equals(ErrorField.USER_NAME)).map(Error::getMessage).collect(Collectors.toList());
            this.passwordErrors = errorList.stream().filter(e -> e.getField().equals(ErrorField.PASSWORD)).map(Error::getMessage).collect(Collectors.toList());
            this.emailErrors = errorList.stream().filter(e -> e.getField().equals(ErrorField.EMAIL)).map(Error::getMessage).collect(Collectors.toList());
            this.regErrors = errorList.stream().filter(e -> e.getField().equals(ErrorField.CLIENT)).map(Error::getMessage).collect(Collectors.toList());
            logger.debug(this.userNameErrors.toString());
        }

        return null;
    }
}