package com.eitanyacov.agentclientsmangment.service;

import com.eitanyacov.agentclientsmangment.entity.Client;
import com.eitanyacov.agentclientsmangment.exceptions.ClientNotFoundException;
import com.eitanyacov.agentclientsmangment.repository.ClientRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AgentService {

    private ClientRepo clientRepo;

    public Client getClientById(int id) throws ClientNotFoundException {
        return clientRepo.findById(id).orElseThrow(()-> new ClientNotFoundException("cannot find client with id: " + id));
    }

    public List<Client> getClientByName(String name) {
            return clientRepo.findClientByName(name);
    }

    public Client addClient(Client client) {
        return clientRepo.save(client);
    }

    public Client findClientByEmail(String email) {
        return clientRepo.findClientByEmail(email);
    }
}
