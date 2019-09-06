package com.hackathon.cardservice.service;

import com.hackathon.cardservice.constants.Response;
import com.hackathon.cardservice.exception.CardException;
import com.hackathon.cardservice.model.Users;
import com.hackathon.cardservice.model.userlogin.UserLogin;
import com.hackathon.cardservice.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static final Logger log = LogManager.getLogger(CardService.class.getName());
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;

    }
    public boolean verifyUser(UserLogin userLogin) throws CardException {
        List<Users> userList= userRepository.getUserFromUsername(userLogin.getUserName());
        if(userList.isEmpty() || userList.size() >1){
            throw new CardException(Response.USER_NOTFOUND, HttpStatus.BAD_REQUEST);
        }
        return (userList.get(0).getPassword().equals(userLogin.getPassword())) ? true : false;
    }
    public void regisUSer(Users user){
        userRepository.save(user);
    }

}
