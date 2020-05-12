package com.kmzko.configurator.services.detailService;

import com.kmzko.configurator.entity.user.*;
import com.kmzko.configurator.exeption.EmailExistException;
import com.kmzko.configurator.repositories.RoleRepo;
import com.kmzko.configurator.repositories.UserRepo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements DetailService<User> {
    private final UserRepo userRepository;
    private final RoleRepo roleRepository;
    private final PersonalConveyorDetailService conveyorDetailService;
    private final PersonalQuestionnaireDetailService questionnaireDetailService;
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepository, RoleRepo roleRepository,
                       PersonalConveyorDetailService conveyorDetailService,
                       PersonalQuestionnaireDetailService questionnaireDetailService,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.conveyorDetailService = conveyorDetailService;
        this.questionnaireDetailService = questionnaireDetailService;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return createNewUser(user);
    }

    private User createNewUser(User user) throws EmailExistException {
        Role role = roleRepository.findByName(UserRoles.ROLE_USER.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(role));
        user.setStatus(AccountStatus.ACTIVE);

        try {
            return userRepository.save(user);
        }
        catch (DataIntegrityViolationException e) {
            throw new EmailExistException("Email: " + user.getEmail() + " is exist");
        }
    }

    @Override
    public boolean delete(User user) {
        try {
            userRepository.delete(user);
        }
        catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteById(long id) {
        try {
            userRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }

    public List<ConveyorProject> getAllConveyorProjects(long id) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get().getConveyorProjects();
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public List<ConveyorProject> getAllConveyorProjects(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get().getConveyorProjects();
        }
        else {
            throw new UsernameNotFoundException("Username: " + username + "not found");
        }
    }

    public Optional<ConveyorProject> getConveyorProjectById(String username, long id) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get().getConveyorProjects().stream().filter(v -> v.getId().equals(id)).findFirst();
        }
        else {
            throw new UsernameNotFoundException("Username: " + username + "not found");
        }
    }
}
