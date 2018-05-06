package hu.unideb.inf.prtpk.moneyBox.service.validator;

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
     * <pre>LoggerFactory a logoláshoz.</pre>
     */
    private static Logger logger = LoggerFactory.getLogger(EntityManagerFactoryUtil.class);

    /**
     * Hibák listája.
     */
    private List<ErrorEnum> errorList;

    /**
     * <pre>Falhasználónév ellenőrzése.</pre>
     * <ul>
     * <li>A felhasználónév legalább három karakter kell hogy legyen</li>
     * </ul>
     *
     * @param client {@link Client}
     */
    private void nameIsValid(Client client) {
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
     *
     * @param client {@link Client}
     */
    private void passwordIsValid(Client client) {
        logger.info("Validate Client password");
        if (client.getPassword().length() <= 3) {
            errorList.add(ErrorEnum.SHORT_PASSWORD);
            logger.warn("Client password is too short: " + client.getPassword().length());
        }
    }

    /**
     * <pre>E-mail cím ellenőrzése.</pre>
     *
     * @param client {@link Client}
     * @see EmailValidator
     */
    private void emailIsValid(Client client) {
        logger.info("Validate Client email address");
        if (!EmailValidator.getInstance().isValid(client.getEmail())){
            errorList.add(ErrorEnum.INVALID_EMAIL);
            logger.warn("Client email is invalid! " + client.getEmail());
        }
    }

    @Override
    public List<ErrorEnum> validate(Client client) {
        logger.info("Validate Client");
        errorList = new ArrayList<>();

        nameIsValid(client);
        passwordIsValid(client);
        emailIsValid(client);

        return errorList;
    }
}