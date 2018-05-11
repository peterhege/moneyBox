package hu.unideb.inf.prtpk.moneyBox.controller;

import hu.unideb.inf.prtpk.moneyBox.model.Client;
import hu.unideb.inf.prtpk.moneyBox.service.EntityServiceImpl;
import hu.unideb.inf.prtpk.moneyBox.service.api.EntityService;
import hu.unideb.inf.prtpk.moneyBox.Error.Error;
import hu.unideb.inf.prtpk.moneyBox.utility.SessionUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * <pre>Bejelenetkezést végző osztály.</pre>
 */
@ManagedBean
@SessionScoped
public class ClientController {

    /**
     * Felhasználónév.
     */
    private String userName;

    /**
     * Felhasználó e-mail címe.
     */
    private String email;

    /**
     * Jelszó.
     */
    private String password;

    /**
     * Felhasználó entitása.
     */
    private Client client;

    /**
     * Bejelentkezéskor fellépő hibák listája.
     */
    private List<String> loginErrors;

    /**
     * Regisztrációkor fellépő hibák listája;
     */
    private List<String> regErrors;

    /**
     * Entitások kezelése.
     */
    private EntityService entityService = new EntityServiceImpl();

    /**
     * Session kezelése.
     */
    private HttpSession session = SessionUtil.getSession();

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
        Optional<Client> optionalClient = entityService.findClientByNameAndPass(userName, password);
        if (optionalClient.isPresent()){
            this.client = optionalClient.get();
            this.loginErrors = null;
        }
        else loginErrors.add("Hibás felhasználónév vagy jelszó.");
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
        Client client = new Client(userName, password, email);
        List<Error> errorList = entityService.createClient(client);
        if (errorList.size() == 0){
            this.client = client;
            this.regErrors = null;
        }

        return null;
    }
}