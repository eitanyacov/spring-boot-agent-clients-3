package com.eitanyacov.agentclientsmangment.controller;

import com.eitanyacov.agentclientsmangment.entity.Agent;
import com.eitanyacov.agentclientsmangment.entity.Client;
import com.eitanyacov.agentclientsmangment.exceptions.AgentNotFoundException;
import com.eitanyacov.agentclientsmangment.exceptions.ClientNotFoundException;
import com.eitanyacov.agentclientsmangment.repository.AgentRepo;
import com.eitanyacov.agentclientsmangment.repository.ClientRepo;
import com.eitanyacov.agentclientsmangment.service.AgentService;
import com.eitanyacov.agentclientsmangment.service.MangerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping("/api/agent")
public class AgentController {

    private AgentService agentService;
    private ClientRepo clientRepo;
    private MangerService mangerService;
    private AgentRepo agentRepo;

    @GetMapping("/{id}")
    public ResponseEntity<?> findClientById(@PathVariable int id) {
        try{
            Client client = agentService.getClientById(id);
            return new ResponseEntity<>(client, HttpStatus.OK);
        }catch (ClientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/add-client-by-agent/{id}")
    public ResponseEntity<?> addClientToAgentByAgent(@PathVariable int id, @RequestBody Client client) {
        try{
            Agent agent = mangerService.getAgentById(id);
            Client newClient = agentService.addClient(client);
            newClient.setSignedAt(new Date());
            agent.addClientToAgent(newClient);
            clientRepo.save(newClient);
            agentRepo.save(agent);
            return new ResponseEntity<>(newClient, HttpStatus.OK);

        }catch (AgentNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
