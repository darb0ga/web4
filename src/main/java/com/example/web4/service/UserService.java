package com.example.web4.service;


import com.example.web4.dto.UserDto;
import com.example.web4.entity.User;
import com.example.web4.repository.UserRepository;
import com.example.web4.utils.PasswordHasher;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    private  UserRepository userRepo;
    private  PasswordHasher hashPass;


    public UserService(UserRepository userRepo, PasswordHasher hasher) {
        this.userRepo = userRepo;
        this.hashPass = hasher;
    }


    @Transactional
    public User getByLogin(String login) {
        return userRepo.getByLogin(login);
    }

    @Transactional
    public User getByLoginAndPassword(String login, String password) {
        return userRepo.getByLoginAndPassword(login, password);
    }
    @Transactional
    public User getById(Long id) {
        return userRepo.getById(id);
    }


    @Transactional
    public boolean existsByLogin(String name){
        return userRepo.existsByLogin(name);
    }

    @Transactional
    public boolean register(String login, String password){
        try {
            User newUser = new User();
            newUser.setLogin(login);
            newUser.setPassword(password);
            userRepo.save(newUser);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
