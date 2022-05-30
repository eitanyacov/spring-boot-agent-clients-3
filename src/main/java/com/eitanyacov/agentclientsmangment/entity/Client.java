package com.eitanyacov.agentclientsmangment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    private Date signedAt;

    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

}
