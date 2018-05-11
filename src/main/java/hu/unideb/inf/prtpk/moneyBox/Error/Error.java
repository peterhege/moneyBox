package hu.unideb.inf.prtpk.moneyBox.Error;

/**
 * <pre>Hibák megjelenítésére szolgáló osztály.</pre>
 */
public class Error {

    /**
     * Mely mezőnél keletkezett a probléma.
     */
    private ErrorField field;

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
    public Error(ErrorField field, String message) {
        this.field = field;
        this.message = message;
    }

    /**
     * @return {@link #field}
     */
    public ErrorField getField() {
        return field;
    }

    /**
     * @param field {@link #field}
     */
    public void setField(ErrorField field) {
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
