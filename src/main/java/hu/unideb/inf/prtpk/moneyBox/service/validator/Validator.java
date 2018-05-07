package hu.unideb.inf.prtpk.moneyBox.service.validator;

import java.util.List;

/**
 * <pre>Validator interfész az ellenőrzésekhez.</pre>
 *
 * @param <T> Vizsgálandó entitás
 */
public interface Validator<T> {

    /**
     * <pre>Entitás ellenőrzése.</pre>
     *
     * @param t    Entitás példánya
     * @param type Milyen műveletnél ellenőrzünk? {@link ValidateType}
     * @return Hibák listája.
     */
    List<ErrorEnum> validate(T t, ValidateType type);
}
