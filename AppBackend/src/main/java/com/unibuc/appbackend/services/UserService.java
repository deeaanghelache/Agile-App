package com.unibuc.appbackend.services;

import com.unibuc.appbackend.entities.User;
import com.unibuc.appbackend.exceptions.UserAlreadyExistsException;
import com.unibuc.appbackend.exceptions.UserNotFoundException;
import com.unibuc.appbackend.interfaces.UserRepository;
import com.unibuc.appbackend.interfaces.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserRoleService userRoleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, UserRoleService userRoleService) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
    }

    public User create(User user) {
        userRepository.findByEmail(user.getEmail()).ifPresent(ex -> {
            throw new UserAlreadyExistsException();
        });
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRoleService.addRoleForUser(user);
        return userRepository.save(user);
    }

    public User login(User user) {
        Optional<User> userFromDB = userRepository.findByEmail(user.getEmail());
        if (userFromDB.isPresent()) {
            if (bCryptPasswordEncoder.matches(user.getPassword(), userFromDB.get().getPassword())){
                return userFromDB.get();
            }
        }
        return null;
    }

    public User getUserById(UUID uuid) {
        Optional<User> user = userRepository.findById(uuid);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException();
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void changePassword(UUID uuid, String password) {
        Optional<User> user = userRepository.findById(uuid);
        if (user.isPresent()) {
            user.get().setPassword(bCryptPasswordEncoder.encode(password));
            userRepository.save(user.get());
        } else {
            throw new UserNotFoundException();
        }
    }

    public void delete(UUID uuid) {
        Optional<User> user = userRepository.findById(uuid);
        if (user.isPresent()) {
            userRepository.deleteById(uuid);
        } else {
            throw new UserNotFoundException();
        }
    }
}
