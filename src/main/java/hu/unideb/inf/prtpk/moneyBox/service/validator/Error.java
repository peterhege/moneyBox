package hu.unideb.inf.prtpk.moneyBox.service.validator;

/**
 * <pre>Hibák megjelenítésére szolgáló osztály.</pre>
 */
public class Error {

    /**
     * Mely mezőnél keletkezett a probléma.
     */
    private String field;

    /**
     * A hiba leírása.
     */
    private String message;

    /**
     * <pre>Konstruktor.</pre>
     *
     * @param field   {@link #field}
     * @param message {@link #message}
     */
    public Error(String field, String message) {
        this.field = field;
        this.message = message;
    }

    /**
     * @return {@link #field}
     */
    public String getField() {
        return field;
    }

    /**
     * @param field {@link #field}
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * @return {@link #message}
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message {@link #message}
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Error{" +
                "field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
