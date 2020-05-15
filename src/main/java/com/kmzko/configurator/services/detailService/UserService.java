package com.kmzko.configurator.services.detailService;

import com.kmzko.configurator.dto.user.BioDto;
import com.kmzko.configurator.dto.user.UserDto;
import com.kmzko.configurator.entity.user.*;
import com.kmzko.configurator.exeption.EmailExistException;
import com.kmzko.configurator.mappers.UserBioMapper;
import com.kmzko.configurator.mappers.UserMapper;
import com.kmzko.configurator.repositories.RoleRepo;
import com.kmzko.configurator.repositories.UserRepo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements DetailService<UserDto> {
    private final UserRepo userRepository;
    private final RoleRepo roleRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private final UserMapper mapper;
    private final UserBioMapper userBioMapper;

    public UserService(UserRepo userRepository, RoleRepo roleRepository,
                       BCryptPasswordEncoder passwordEncoder, UserMapper mapper, UserBioMapper userBioMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
        this.userBioMapper = userBioMapper;
    }

    public Optional<BioDto> updateUserInformation(BioDto bioDto, String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            user.get().setName(bioDto.getName());
            user.get().setEmail(bioDto.getEmail());
            return Optional.of(userBioMapper.toDto(user.get()));
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<UserDto> findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return Optional.of(mapper.toDto(user.get()));
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<UserDto> findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return Optional.of(mapper.toDto(user.get()));
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getById(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return Optional.of(mapper.toDto(user.get()));
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public UserDto save(UserDto user) {
        return createNewUser(user);
    }

    private UserDto createNewUser(UserDto user) throws EmailExistException {
        User newUser = mapper.toEntity(user);
        Role role = roleRepository.findByName(UserRoles.ROLE_USER.toString());

        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRoles(Collections.singleton(role));
        newUser.setStatus(AccountStatus.ACTIVE);

        try {
            return mapper.toDto(userRepository.save(newUser));
        }
        catch (DataIntegrityViolationException e) {
            throw new EmailExistException("Email: " + user.getEmail() + " is exist");
        }
    }

    @Override
    public boolean delete(UserDto user) {
        try {
            userRepository.delete(mapper.toEntity(user));
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
