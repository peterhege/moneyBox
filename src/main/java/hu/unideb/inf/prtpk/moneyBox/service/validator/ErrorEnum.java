package hu.unideb.inf.prtpk.moneyBox.service.validator;

/**
 * <pre>Validálási hibákhoz beszédes nevek.</pre>
 */
public enum ErrorEnum {
    /** Túl rövid név. */
    SHORT_NAME,
    /** Túl rövid jelszó. */
    SHORT_PASSWORD,
    /** Hibás e-mail cím. */
    INVALID_EMAIL
}
