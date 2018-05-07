package hu.unideb.inf.prtpk.moneyBox.service.validator;

import hu.unideb.inf.prtpk.moneyBox.dao.api.ClientDAO;
import hu.unideb.inf.prtpk.moneyBox.model.Client;
import hu.unideb.inf.prtpk.moneyBox.utility.EntityManagerFactoryUtil;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>Ügyfelek adatainak ellenőrzése.</pre>
 */
public class ClientValidator implements Validator<Client> {

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
     * Vizsgálandó {@link Client}.
     */
    private Client client;

    /**
     * <pre>Konstruktor.</pre>
     *
     * @param clientDAO {@link ClientDAO}
     */
    public ClientValidator(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    /**
     * <pre>Falhasználónév ellenőrzése.</pre>
     * <ul>
     * <li>A felhasználónév legalább három karakter kell hogy legyen</li>
     * </ul>
     */
    private void nameIsValid() {
        logger.info("Validate Client username");
        if (client.getClientName().length() <= 3) {
            errorList.add(ErrorEnum.SHORT_NAME);
            logger.warn("Client username is too short: " + client.getClientName().length());
        }
    }

    /**
     * <pre>Jelszó ellenőrzése.</pre>
     * <ul>
     * <li>A jelszó legalább három karakter kell hogy legyen</li>
     * </ul>
     */
    private void passwordIsValid() {
        logger.info("Validate Client password");
        if (client.getPassword().length() <= 3) {
            errorList.add(ErrorEnum.SHORT_PASSWORD);
            logger.warn("Client password is too short: " + client.getPassword().length());
        }
    }

    /**
     * <pre>E-mail cím ellenőrzése.</pre>
     *
     * @see EmailValidator
     */
    private void emailIsValid() {
        logger.info("Validate Client email address");
        if (!EmailValidator.getInstance().isValid(client.getEmail())) {
            errorList.add(ErrorEnum.INVALID_EMAIL);
            logger.warn("Client email is invalid! " + client.getEmail());
        }
    }

    /**
     * <pre>Annak vizsgálata, hogy létezik-e már ilyen ügyfél.</pre>
     * <ul>
     * <li>Létezik ilyen felhasználónév</li>
     * <li>Létezik ilyen e-mail cím</li>
     * </ul>
     */
    private void clientIsExist() {
        logger.info("Validate Client is exist");
        if (clientDAO.findByEmail(client.getEmail()).isPresent()) {
            errorList.add(ErrorEnum.EXIST_EMAIL);
            logger.warn("E-mail is already exist!");
        }
        if (clientDAO.findByName(client.getClientName()).isPresent()) {
            errorList.add(ErrorEnum.EXIST_USERNAME);
            logger.warn("Username is already exist!");
        }
    }

    /**
     * <pre>Ügyfél azonosító keresése. Ha nem található, hiba.</pre>
     */
    private void clientIdIsNotExist() {
        logger.info("Validate Client ID is exist");
        if (!clientDAO.findById(client.getId()).isPresent()) {
            errorList.add(ErrorEnum.NOT_EXIST_ID);
            logger.warn("ID not found!");
        }
    }

    @Override
    public List<ErrorEnum> validate(Client client, ValidateType type) {
        logger.info("Validate Client");
        this.client = client;
        this.errorList = new ArrayList<>();

        switch (type) {
            case CREATE:
                nameIsValid();
                passwordIsValid();
                emailIsValid();
                clientIsExist();
                break;
            case UPDATE:
                nameIsValid();
                passwordIsValid();
                emailIsValid();
                clientIdIsNotExist();
        }

        return errorList;
    }
}
