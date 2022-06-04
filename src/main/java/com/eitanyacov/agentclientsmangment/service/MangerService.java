package com.eitanyacov.agentclientsmangment.service;

import com.eitanyacov.agentclientsmangment.entity.Agent;
import com.eitanyacov.agentclientsmangment.entity.Client;
import com.eitanyacov.agentclientsmangment.exceptions.AgentNotFoundException;
import com.eitanyacov.agentclientsmangment.exceptions.NoAgentsToShowException;
import com.eitanyacov.agentclientsmangment.exceptions.NoClientsToShowException;
import com.eitanyacov.agentclientsmangment.repository.AgentRepo;
import com.eitanyacov.agentclientsmangment.repository.ClientRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MangerService {

    private AgentRepo agentRepo;
    private ClientRepo clientRepo;

    public Agent addAgent(Agent agent) {
        return agentRepo.save(agent);
    }

    public Agent getAgentById(int id) throws AgentNotFoundException {
            Optional<Agent> opt = agentRepo.findById(id);
            if(opt.isPresent()) {
                return opt.get();
            }else {
                throw new AgentNotFoundException("cannot find agent with id: " + id);
            }

    }

    public List<Agent> getAllAgents() throws NoAgentsToShowException {
        List<Agent> agents = agentRepo.findAll();
        if(agents == null) {
            throw new NoAgentsToShowException("No Agents to show");
        }else {
            return agents;
        }
    }

    public List<Client> getAllCompanyClients() throws NoClientsToShowException {
        List<Client> clients = clientRepo.findAll();
        if(clients == null) {
            throw new NoClientsToShowException("No Clients to show");
        }else {
            return clients;
        }
    }


}
