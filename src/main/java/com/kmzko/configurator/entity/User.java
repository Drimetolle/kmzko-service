package com.kmzko.configurator.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable=false, unique=true)
    private String email;
    private String name = "user";
    private String password = UUID.randomUUID().toString();
    @ManyToMany
    private Set<Role> roles;
}
