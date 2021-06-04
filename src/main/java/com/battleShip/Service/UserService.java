package com.battleShip.Service;

import com.battleShip.Model.Entities.UserLogin;
import com.battleShip.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserLogin> findAllUser() {
        List<UserLogin> deletePass = userRepository.getUser();
        for (int i=0;i <  deletePass.size(); i++  ) {
            deletePass.get(i).setPassword("*********");

        }
        return deletePass;
    }

    public UserLogin getLogin (String email, String password) {

        return userRepository.getByLogin(email,password);


    }

    public UserLogin getUserById (int id) {

        return userRepository.getUserById(id);


    }


    public UserLogin createUser(UserLogin user) {
        userRepository.save(user);
        return user;
    }

}