package com.kmzko.configurator.services;

import com.kmzko.configurator.entity.user.User;
import com.kmzko.configurator.exeption.EmailExist;
import com.kmzko.configurator.repositories.UserRepo;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;

@Service
public class RegistrationService {
    private final UserRepo repository;

    public RegistrationService(UserRepo repository) {
        this.repository = repository;
    }

    public User createNewUser(User user) throws EmailExist {
        try {
            return repository.save(user);
        }
        catch (DataIntegrityViolationException e) {
            throw new EmailExist("Email: " + user.getEmail() + " is exist");
        }
    }
}
