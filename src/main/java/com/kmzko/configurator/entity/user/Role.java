package com.kmzko.configurator.entity.user;

import com.kmzko.configurator.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role extends AbstractEntity {
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
