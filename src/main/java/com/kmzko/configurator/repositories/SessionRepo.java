package com.kmzko.configurator.repositories;

import com.kmzko.configurator.entity.Session;
import com.kmzko.configurator.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepo extends JpaRepository<Session, Long> {
    void deleteByToken(String token);
    Session findByToken(String token);
    Session findByUser(User user);
}
