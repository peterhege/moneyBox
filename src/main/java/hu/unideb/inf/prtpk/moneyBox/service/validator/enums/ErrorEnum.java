package hu.unideb.inf.prtpk.moneyBox.service.validator.enums;

/**
 * <pre>Validálási hibákhoz beszédes nevek.</pre>
 */
public enum ErrorEnum {
    /**
     * Túl rövid név.
     */
    SHORT_NAME,
    /**
     * Túl rövid jelszó.
     */
    SHORT_PASSWORD,
    /**
     * Hibás e-mail cím.
     */
    INVALID_EMAIL,
    /**
     * Hibás url cím.
     */
    INVALID_URL,
    /**
     * Hibás összeg.
     */
    INVALID_PRICE,
    /**
     * Az e-mail cím már foglalt.
     */
    EXIST_EMAIL,
    /**
     * A felhasználónév már foglalt.
     */
    EXIST_USERNAME,
    /**
     * Nem található az azonosító.
     */
    NOT_EXIST_ID,
    /**
     * Nincs összeg megadva.
     */
    NOT_EXIST_PRICE
}
