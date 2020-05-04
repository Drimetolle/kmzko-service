package com.kmzko.configurator.services.detailService;

import com.kmzko.configurator.entity.user.*;
import com.kmzko.configurator.entity.user.conveyor.PersonalConveyor;
import com.kmzko.configurator.entity.user.questionnaire.PersonalQuestionnaire;
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

    public User findByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        }
        else {
            throw new UsernameNotFoundException("Username: " + username + "not found");
        }
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
        user.setStatus(Status.ACTIVE);

        try {
            return userRepository.save(user);
        }
        catch (DataIntegrityViolationException e) {
            throw new EmailExistException("Email: " + user.getEmail() + " is exist");
        }
    }

    @Override
    public User update(User user) {
        return null;
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
            return user.get().getConveyorProject();
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public List<ConveyorProject> getAllConveyorProjects(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get().getConveyorProject();
        }
        else {
            throw new UsernameNotFoundException("Username: " + username + "not found");
        }
    }

//    public Set<PersonalConveyor> getAllUserConveyors(long id) {
//        Optional<User> user = userRepository.findById(id);
//        if (user.isPresent()) {
//            return user.get().getConveyorProject().getConveyor();
//        }
//        else {
//            return new HashSet<>();
//        }
//    }
//
//    public Set<PersonalQuestionnaire> getAllUserQuestionnaires(long id) {
//        Optional<User> user = userRepository.findById(id);
//        if (user.isPresent()) {
//            return user.get().getQuestionnaires();
//        }
//        else {
//            return new HashSet<>();
//        }
//    }
//
//    public Set<PersonalConveyor> getAllUserConveyors(String name) {
//        return userRepository.findByUsername(name).getConveyors();
//    }
//
//    public Set<PersonalQuestionnaire> getAllUserQuestionnaires(String name) {
//        return userRepository.findByUsername(name).getQuestionnaires();
//    }
//
//    public Optional<PersonalConveyor> getUserConveyorsById(long id, String name) {
//        Optional<PersonalConveyor> result = conveyorDetailService.getById(id);
//        if (result.isPresent()) {
//            return result.get().getUser().getUsername().equals(name) ? result : Optional.empty();
//        }
//
//        return result;
//    }
//
//    public Optional<PersonalQuestionnaire> getUserQuestionnairesById(long id, String name) {
//        Optional<PersonalQuestionnaire> result = questionnaireDetailService.getById(id);
//        if (result.isPresent()) {
//            return result.get().getUser().getUsername().equals(name) ? result : Optional.empty();
//        }
//
//        return result;
//    }
}
