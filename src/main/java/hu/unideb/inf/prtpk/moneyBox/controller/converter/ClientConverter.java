package hu.unideb.inf.prtpk.moneyBox.controller.converter;

import hu.unideb.inf.prtpk.moneyBox.controller.model.ClientView;
import hu.unideb.inf.prtpk.moneyBox.model.Client;
import org.modelmapper.ModelMapper;

public class ClientConverter implements Converter<ClientView, Client> {

    /**
     * @see ModelMapper
     */
    private ModelMapper mapper = new ModelMapper();

    @Override
    public ClientView from(Client client) {
        return mapper.map(client, ClientView.class);
    }

    @Override
    public Client to(ClientView clientView) {
        return mapper.map(clientView, Client.class);
    }
}
