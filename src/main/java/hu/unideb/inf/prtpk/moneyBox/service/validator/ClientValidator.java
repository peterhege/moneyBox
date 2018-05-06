package hu.unideb.inf.prtpk.moneyBox.service.validator;

import hu.unideb.inf.prtpk.moneyBox.model.Client;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>Ügyfelek adatainak ellenőrzése.</pre>
 */
public class ClientValidator implements Validator<Client> {

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
        if (client.getClientName().length() <= 3) errorList.add(ErrorEnum.SHORT_NAME);
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
        if (client.getPassword().length() <= 3) errorList.add(ErrorEnum.SHORT_PASSWORD);
    }

    /**
     * <pre>E-mail cím ellenőrzése.</pre>
     *
     * @param client {@link Client}
     * @see EmailValidator
     */
    private void emailIsValid(Client client) {
        if (!EmailValidator.getInstance().isValid(client.getEmail())) errorList.add(ErrorEnum.INVALID_EMAIL);
    }

    @Override
    public List<ErrorEnum> validate(Client client) {
        errorList = new ArrayList<>();

        nameIsValid(client);
        passwordIsValid(client);
        emailIsValid(client);

        return errorList;
    }
}
