package com.eitanyacov.agentclientsmangment.controller;

import com.eitanyacov.agentclientsmangment.entity.Agent;
import com.eitanyacov.agentclientsmangment.entity.Client;
import com.eitanyacov.agentclientsmangment.exceptions.AgentNotFoundException;
import com.eitanyacov.agentclientsmangment.exceptions.NoAgentsToShowException;
import com.eitanyacov.agentclientsmangment.exceptions.NoClientsToShowException;
import com.eitanyacov.agentclientsmangment.repository.AgentRepo;
import com.eitanyacov.agentclientsmangment.repository.ClientRepo;
import com.eitanyacov.agentclientsmangment.service.AgentService;
import com.eitanyacov.agentclientsmangment.service.MangerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/manger")
public class MangerController {

    private MangerService mangerService;
    private AgentRepo agentRepo;
    private AgentService agentService;
    private ClientRepo clientRepo;

    @PostMapping("/add-agent")
    public ResponseEntity<Agent> addAgent(@RequestBody Agent agent) {
        Agent newAgent = mangerService.addAgent(agent);
        newAgent.setStartedAt(new Date());
        agentRepo.save(newAgent);
        return new ResponseEntity<>(newAgent, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAgentById(@PathVariable int id)  {
       try{
           Agent agent = mangerService.getAgentById(id);
           return new ResponseEntity<>(agent, HttpStatus.OK);
       }catch (AgentNotFoundException e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping("/get-clients-by-agent-id/{id}")
    public ResponseEntity<?> getAllClientsByAgentId(@PathVariable int id) {
        try{
            Agent agent = mangerService.getAgentById(id);
            List<Client> clients = agent.getClients();
            return new ResponseEntity<>(clients, HttpStatus.OK);
        }catch (AgentNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

//    @PostMapping("/add-client/{id}")
//    public ResponseEntity<?> addClientToAgentByManger(@PathVariable int id, @RequestBody Client client) throws AgentNotFoundException {
//        try{
//            Agent agent = mangerService.getAgentById(id);
//            Client newClient = agentService.addClient(client);
//            newClient.setSignedAt(new Date());
//            newClient.setAgent(agent);
//            clientRepo.save(newClient);
//            return new ResponseEntity<Client>(newClient, HttpStatus.CREATED);
//        }catch (AgentNotFoundException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }

    @PostMapping("/add-client/{id}")
    public ResponseEntity<?> addClientToAgentByManger(@PathVariable int id, @RequestBody Client client) throws AgentNotFoundException {
        try{
            Agent agent = mangerService.getAgentById(id);
            Client newClient = clientRepo.save(client);
            newClient.setSignedAt(new Date());
            newClient.setAgent(agent);
            clientRepo.save(newClient);
            return new ResponseEntity<Client>(newClient, HttpStatus.CREATED);
        }catch (AgentNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all/agents")
    public ResponseEntity<?> getAllAgents() {
        try{
            mangerService.getAllAgents();
        }catch (NoAgentsToShowException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(agentRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/all/company-clients")
    public ResponseEntity<?> getAllCompanyClients() {
       try{
             mangerService.getAllCompanyClients();
       } catch (NoClientsToShowException e) {
           e.printStackTrace();
           return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<>(clientRepo.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteClientById(@PathVariable int id) {
         clientRepo.deleteById(id);
         return "deleted client with id: " + id;
    }

    //this method is working fine, but in the method below i'm trying to throw exception if cannot find agent by id.
//    @PutMapping
//    public ResponseEntity<?> updateAgent(@RequestBody Agent agent) {
//
//        Agent agentToUpdate = agentRepo.findById(agent.getId()).get();
//        agentToUpdate.setName(agent.getName());
//        agentToUpdate.setEmail(agent.getEmail());
//        agentRepo.save(agentToUpdate);
//        return new ResponseEntity<>(agentToUpdate, HttpStatus.OK);
//    }

    @PutMapping
    public ResponseEntity<?> updateAgent(@RequestBody Agent agent) throws AgentNotFoundException {
        try{
            Agent agentToUpdate = mangerService.getAgentById(agent.getId());
            agentToUpdate.setName(agent.getName());
            agentToUpdate.setEmail(agent.getEmail());
            agentRepo.save(agentToUpdate);
            return new ResponseEntity<>(agentToUpdate, HttpStatus.OK);
        }catch (AgentNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}
