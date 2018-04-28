package hu.unideb.inf.prtpk.moneyBox.dao.api;

import java.io.Serializable;
import java.util.Optional;

/**
 * Generikus DAO | Generic DAO
 * @param <T> Entity típusa | Entity type
 * @param <ID> Az Entity azonosítójának típusa | Type of Entity's ID
 */
public interface GenericDAO<T, ID> {

    /**
     * Példányosítás | Make an instance
     * @param entity Entity
     */
    void persist(T entity);

    /**
     * Entity keresése ID alapján | Find an entity by ID
     * @param id Entity azonosítója | An entity's ID
     * @return T Entity
     */
    Optional<T> findById(ID id);

    /**
     * Entity eltávolítása | Remove an entity
     * @param entity Entity
     */
    void remove(T entity);

}
