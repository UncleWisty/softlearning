package com.example.core.entities.client.appservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.example.core.entities.client.dto.ClientDTO;
import com.example.core.entities.client.mapper.ClientMapper;
import com.example.core.entities.client.persistence.ClientRepository;
import com.example.services.serializers.Serializer;
import com.example.services.serializers.Serializers;
import com.example.services.serializers.SerializersCatalog;
import com.example.shared.exceptions.BuildException;
import com.example.shared.exceptions.ServiceException;

@Controller
public class ClientServicesImpl implements ClientServices {

    @Autowired
    private ClientRepository clientRepository;
    private Serializer<ClientDTO> serializer;

    // ****** Implementing the business logic methods and common features (clean code design) ******
    protected ClientDTO getDTO(int id) {
        return clientRepository.findByIdClient(id).orElse(null);
    }

    protected ClientDTO getByIdClient(int id) throws ServiceException {
        ClientDTO cdto = this.getDTO(id);

        if (cdto == null) {
            throw new ServiceException("client " + id + " not found");
        }
        return cdto;
    }

    protected ClientDTO checkInputData(String client) throws ServiceException {
        try {
            ClientDTO cdto = (ClientDTO) this.serializer.deserialize(client, ClientDTO.class);
            ClientMapper.DTOtoClient(cdto);
            return cdto;
        } catch (BuildException e) {
            throw new ServiceException("error in the input client data: " + e.getMessage());
        }
    }

    @Override
    public String getAllClientsToJson() throws ServiceException {
        Serializer<Iterable<ClientDTO>> listSerializer = (Serializer<Iterable<ClientDTO>>) SerializersCatalog.getInstance(Serializers.JSON_CLIENT);
        return listSerializer.serialize(clientRepository.findAll());
    }

    protected ClientDTO getById(int id) throws ServiceException {
        return this.getByIdClient(id);
    }

    protected ClientDTO newClient(String client) throws ServiceException {
        ClientDTO cdto = this.checkInputData(client);

        if (this.getDTO(cdto.getIdClient()) == null) {
            return clientRepository.save(cdto);
        }
        throw new ServiceException("client " + cdto.getIdClient() + " already exists");
    }

    protected ClientDTO updateClient(String client) throws ServiceException {
        ClientDTO cdto = this.checkInputData(client);
        this.getByIdClient(cdto.getIdClient());
        return clientRepository.save(cdto);
    }

    // ****** Implementing the interface methods ******
    @Override
    public String getByIdClientToJson(int id) throws ServiceException {
        this.serializer = (Serializer<ClientDTO>) SerializersCatalog.getInstance(Serializers.JSON_CLIENT);
        return serializer.serialize(this.getById(id));
    }

    @Override
    public String getByIdClientToXml(int id) throws ServiceException {
        this.serializer = (Serializer<ClientDTO>) SerializersCatalog.getInstance(Serializers.XML_CLIENT);
        return serializer.serialize(this.getById(id));
    }

    @Override
    public String addFromJson(String client) throws ServiceException {
        this.serializer = (Serializer<ClientDTO>) SerializersCatalog.getInstance(Serializers.JSON_CLIENT);
        return serializer.serialize(this.newClient(client));
    }

    @Override
    public String addFromXml(String client) throws ServiceException {
        this.serializer = (Serializer<ClientDTO>) SerializersCatalog.getInstance(Serializers.XML_CLIENT);
        return serializer.serialize(this.newClient(client));
    }

    @Override
    public String updateOneFromJson(String client) throws ServiceException {
        this.serializer = (Serializer<ClientDTO>) SerializersCatalog.getInstance(Serializers.JSON_CLIENT);
        return serializer.serialize(this.updateClient(client));
    }

    @Override
    public String updateOneFromXml(String client) throws ServiceException {
        this.serializer = (Serializer<ClientDTO>) SerializersCatalog.getInstance(Serializers.XML_CLIENT);
        return serializer.serialize(this.updateClient(client));
    }

    @Override
    @Transactional
    public void deleteByIdClient(int id) throws ServiceException {
        this.getByIdClient(id);
        clientRepository.deleteByIdClient(id);
    }
}
