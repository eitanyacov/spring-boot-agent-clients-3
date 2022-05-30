package com.eitanyacov.agentclientsmangment.repository;

import com.eitanyacov.agentclientsmangment.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepo extends JpaRepository<Agent, Integer> {
}
