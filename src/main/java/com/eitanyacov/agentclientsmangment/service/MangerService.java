package com.eitanyacov.agentclientsmangment.service;

import com.eitanyacov.agentclientsmangment.entity.Agent;
import com.eitanyacov.agentclientsmangment.exceptions.AgentNotFoundException;
import com.eitanyacov.agentclientsmangment.repository.AgentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MangerService {

    private AgentRepo agentRepo;

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
}
