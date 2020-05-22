package com.kmzko.configurator.entity.user;

import com.kmzko.configurator.entity.AbstractEntity;
import com.kmzko.configurator.entity.Session;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends AbstractEntity {
    @Column(nullable=false, unique=true)
    private String email;

    @NotNull
    private String name = "user";

    @Column(nullable=false, unique=true)
    private String username;

    @NotNull
    private String password = UUID.randomUUID().toString();

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @OneToOne(mappedBy = "user")
    private Session session;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ConveyorProject> conveyorProjects = new ArrayList<>();
}
