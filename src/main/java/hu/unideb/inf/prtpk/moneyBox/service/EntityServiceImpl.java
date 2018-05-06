package hu.unideb.inf.prtpk.moneyBox.service;

import hu.unideb.inf.prtpk.moneyBox.dao.api.*;
import hu.unideb.inf.prtpk.moneyBox.model.*;
import hu.unideb.inf.prtpk.moneyBox.service.validator.*;
import hu.unideb.inf.prtpk.moneyBox.service.api.EntityService;
import hu.unideb.inf.prtpk.moneyBox.utility.EntityManagerFactoryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * <pre>EntityService implementálása.</pre>
 *
 * @see EntityService
 */
public class EntityServiceImpl implements EntityService {

    /**
     * <pre>LoggerFactory a logoláshoz.</pre>
     */
    private static Logger logger = LoggerFactory.getLogger(EntityManagerFactoryUtil.class);

    /**
     * Ügyfélkezelő DAO.
     */
    private ClientDAO clientDAO;

    /**
     * Ügyfél ellenőrző.
     */
    private Validator<Client> validator;

    /**
     * <pre>Konstruktor.</pre>
     *
     * @param clientDAO Az ügyfeleket kezelő DAO
     */
    EntityServiceImpl(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
        this.validator = new ClientValidator(clientDAO);
    }

    @Override
    public List<ErrorEnum> createClient(Client client) {
        logger.info("Create Client");
        List<ErrorEnum> errorList = validator.validate(client);
        if (errorList.size() == 0) clientDAO.persist(client);
        return errorList;
    }

    @Override
    public void removeClient(Long id) {
        logger.info("Remove Client");
        Optional<Client> clientOptional = clientDAO.findById(id);
        if (clientOptional.isPresent()) clientDAO.remove(clientOptional.get());
        else {
            String errorString = "Removable Client [" + id + "] not found";
            logger.error(errorString);
            throw new EntityNotFoundException(errorString);
        }
    }

    @Override
    public void updateClient(Client client) {

    }

    @Override
    public void createAndAddProductToClient(Client client, Product product) {

    }
}
