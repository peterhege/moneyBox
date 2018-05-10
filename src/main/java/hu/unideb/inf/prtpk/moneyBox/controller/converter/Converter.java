package hu.unideb.inf.prtpk.moneyBox.controller.converter;

/**
 * <pre>Modellek konvertálása a rétegek között (MVVM).</pre>
 *
 * @param <V> View Modell
 * @param <D> DAO Modell
 */
public interface Converter<V, D> {
    /**
     * <pre>Service-hez szükséges modell előállítása.</pre>
     *
     * @param d DAO-hoz szükséges modell
     * @return View Modell
     */
    V from(D d);

    /**
     * <pre>DAO-hoz szükséges modell előállítása.</pre>
     *
     * @param v Service-hez szükséges modell
     * @return DAO Modell
     */
    D to(V v);
}