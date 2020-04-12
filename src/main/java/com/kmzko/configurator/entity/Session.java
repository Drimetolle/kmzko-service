package com.kmzko.configurator.entity;

import com.kmzko.configurator.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "session")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Session extends AbstractEntity {
    private String token;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
