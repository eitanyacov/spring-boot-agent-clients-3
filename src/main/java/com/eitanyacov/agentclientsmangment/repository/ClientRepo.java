package com.eitanyacov.agentclientsmangment.repository;

import com.eitanyacov.agentclientsmangment.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepo extends JpaRepository<Client, Integer> {

    List<Client> findClientByName(String name);

    Client findClientByEmail(String email);
}
