package com.eitanyacov.agentclientsmangment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private Date startedAt;
    private int numberOfClients;

    @OneToMany
    @JsonIgnore
    private List<Client> clients;


    public void addClientToAgent(Client client) {
        if(clients == null) {
            clients = new ArrayList<Client>();
        }
        client.setAgent(this);
        clients.add(client);
    }

    public void countClients() {
        numberOfClients ++;
    }
}
