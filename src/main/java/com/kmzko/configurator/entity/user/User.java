package com.kmzko.configurator.entity.user;

import com.kmzko.configurator.entity.AbstractEntity;
import com.kmzko.configurator.entity.Session;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends AbstractEntity {
    @Column(nullable=false, unique=true)
    private String email;
    private String username;
    private String name = "user";
    private String password = UUID.randomUUID().toString();
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<PersonalConveyor> conveyors;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<PersonalQuestionnaire> questionnaires;
    @OneToOne(mappedBy = "user")
    private Session session;
}
