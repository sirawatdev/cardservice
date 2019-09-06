package com.hackathon.cardservice.service;

import com.hackathon.cardservice.model.Users;
import com.hackathon.cardservice.model.userlogin.UserLogin;
import com.hackathon.cardservice.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {
    private static final Logger log = LogManager.getLogger(CardService.class.getName());
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;

    }
    public boolean verifyUser(UserLogin userLogin){
        List<Users> userList= userRepository.getUserFromUsername(userLogin.getUsername());
        if(userList.isEmpty() || userList.size() >1){
            return false;
        }
        return (userList.get(0).getPassword().equals(userLogin.getPassword())) ? true : false;
    }

}
