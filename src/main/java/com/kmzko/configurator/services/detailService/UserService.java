package com.kmzko.configurator.services.detailService;

import com.kmzko.configurator.entity.user.PersonalConveyor;
import com.kmzko.configurator.entity.user.PersonalQuestionnaire;
import com.kmzko.configurator.entity.user.Role;
import com.kmzko.configurator.entity.user.User;
import com.kmzko.configurator.exeption.EmailExist;
import com.kmzko.configurator.repositories.RoleRepo;
import com.kmzko.configurator.repositories.UserRepo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements DetailService<User> {
    private final UserRepo userRepository;
    private final RoleRepo roleRepository;
    private final PersonalConveyorDetailService conveyorDetailService;
    private final PersonalQuestionnaireDetailService questionnaireDetailService;

    public UserService(UserRepo userRepository, RoleRepo roleRepository,
                       PersonalConveyorDetailService conveyorDetailService,
                       PersonalQuestionnaireDetailService questionnaireDetailService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.conveyorDetailService = conveyorDetailService;
        this.questionnaireDetailService = questionnaireDetailService;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
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

    private User createNewUser(User user) throws EmailExist {
        Role role = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<Role>() {{ add(role); }});

        try {
            return userRepository.save(user);
        }
        catch (DataIntegrityViolationException e) {
            throw new EmailExist("Email: " + user.getEmail() + " is exist");
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

    public Set<PersonalConveyor> getAllUserConveyors(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get().getConveyors();
        }
        else {
            return new HashSet<>();
        }
    }

    public Set<PersonalQuestionnaire> getAllUserQuestionnaires(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get().getQuestionnaires();
        }
        else {
            return new HashSet<>();
        }
    }

    public Set<PersonalConveyor> getAllUserConveyors(String name) {
        return userRepository.findByUsername(name).getConveyors();
    }

    public Set<PersonalQuestionnaire> getAllUserQuestionnaires(String name) {
        return userRepository.findByUsername(name).getQuestionnaires();
    }

    public Optional<PersonalConveyor> getUserConveyorsById(long id, String name) {
        Optional<PersonalConveyor> result = conveyorDetailService.getById(id);
        if (result.isPresent()) {
            return result.get().getUser().getUsername().equals(name) ? result : Optional.empty();
        }

        return result;
    }

    public Optional<PersonalQuestionnaire> getUserQuestionnairesById(long id, String name) {
        Optional<PersonalQuestionnaire> result = questionnaireDetailService.getById(id);
        if (result.isPresent()) {
            return result.get().getUser().getUsername().equals(name) ? result : Optional.empty();
        }

        return result;
    }
}
