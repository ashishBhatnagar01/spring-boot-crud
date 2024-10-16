package com.example.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> updateUser(long id, User updateUser){
        return userRepository.findById(id)
        .map(user-> {
            user.setName(updateUser.getName());
            user.setEmail(updateUser.getEmail());
            return userRepository.save(user);
        });
    }

    public void deleteUser(long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        } 
    }
}
