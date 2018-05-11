package hu.unideb.inf.prtpk.moneyBox.service;

import hu.unideb.inf.prtpk.moneyBox.dao.ClientDAOImpl;
import hu.unideb.inf.prtpk.moneyBox.dao.ProductDAOImpl;
import hu.unideb.inf.prtpk.moneyBox.dao.api.*;
import hu.unideb.inf.prtpk.moneyBox.model.*;
import hu.unideb.inf.prtpk.moneyBox.service.validator.*;
import hu.unideb.inf.prtpk.moneyBox.service.api.EntityService;
import hu.unideb.inf.prtpk.moneyBox.Error.Error;
import hu.unideb.inf.prtpk.moneyBox.service.validator.api.Validator;
import hu.unideb.inf.prtpk.moneyBox.service.validator.enums.ValidateType;
import hu.unideb.inf.prtpk.moneyBox.utility.EntityManagerFactoryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
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
     * Termékkezelő DAO.
     */
    private ProductDAO productDAO;

    /**
     * Ügyfél ellenőrző.
     */
    private Validator<Client> clientValidator;

    /**
     * Termék ellenőrző.
     */
    private Validator<Product> productValidator;

    /**
     * <pre>Üres konstruktor.</pre>
     */
    public EntityServiceImpl(){
        this.clientDAO = new ClientDAOImpl();
        this.productDAO = new ProductDAOImpl();
        this.clientValidator = new ClientValidator(clientDAO);
        this.productValidator = new ProductValidator(productDAO, clientDAO);
    }

    /**
     * <pre>Konstruktor.</pre>
     *
     * @param clientDAO  Az ügyfeleket kezelő {@link ClientDAO} példánya
     * @param productDAO A termékeket kezelő {@link ProductDAO} példánya
     */
    public EntityServiceImpl(ClientDAO clientDAO, ProductDAO productDAO) {
        this.clientDAO = clientDAO;
        this.productDAO = productDAO;
        this.clientValidator = new ClientValidator(clientDAO);
        this.productValidator = new ProductValidator(productDAO, clientDAO);
    }

    @Override
    public List<Error> createClient(Client client) {
        logger.info("Create Client");
        List<Error> errorList = clientValidator.validate(client, ValidateType.CREATE);
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
    public List<Error> updateClient(Client client) {
        logger.info("Update Client");
        List<Error> errorList = clientValidator.validate(client, ValidateType.UPDATE);
        if (errorList.size() == 0) clientDAO.refresh(client);

        return errorList;
    }

    @Override
    public List<Error> createAndAddProductToClient(Client client, Product product) {
        logger.info("Create Product");
        client.addProduct(product);
        product.setClient(client);
        List<Error> errorList = productValidator.validate(product, ValidateType.CREATE);
        if (errorList.size() == 0) productDAO.merge(product);

        return new ArrayList<>();
    }

    @Override
    public Optional<Client> findClientByNameAndPass(String userName, String password) {
        return clientDAO.findByNameAndPass(userName, password);
    }
}
