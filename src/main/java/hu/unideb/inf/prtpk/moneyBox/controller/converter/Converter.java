package hu.unideb.inf.prtpk.moneyBox.controller.converter;

/**
 * <pre>Modellek konvertálása a rétegek között (MVVM).</pre>
 *
 * @param <T> View Modell
 * @param <P> DAO Modell
 */
public interface Converter<T, P> {
    T from(P p);

    P to(T t);
}