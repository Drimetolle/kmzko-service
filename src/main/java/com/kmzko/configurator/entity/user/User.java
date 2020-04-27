package com.kmzko.configurator.entity.user;

import com.kmzko.configurator.entity.AbstractEntity;
import com.kmzko.configurator.entity.Session;
import com.kmzko.configurator.entity.user.conveyor.PersonalConveyor;
import com.kmzko.configurator.entity.user.questionnaire.PersonalQuestionnaire;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private String name = "user";

    @Column(nullable=false, unique=true)
    private String username;

    @NotNull
    private String password = UUID.randomUUID().toString();

    @Enumerated(EnumType.STRING)
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
