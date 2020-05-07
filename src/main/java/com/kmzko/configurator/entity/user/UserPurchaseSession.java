package com.kmzko.configurator.entity.user;

import com.kmzko.configurator.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_session")
@NoArgsConstructor
@Getter
@Setter
public class UserPurchaseSession extends AbstractEntity {
}