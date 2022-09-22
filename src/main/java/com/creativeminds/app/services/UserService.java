package com.creativeminds.app.services;

import com.creativeminds.app.model.User;
import com.creativeminds.app.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){this.userRepository=userRepository;
    }
    public User createUser(User newUser){
       return this.userRepository.save(newUser);
    }
    public User gerOrCreateuser(Map<String, Object> userData){
        String email = (String) userData.get("email");
        String name = (String) userData.get("nickname");
        String image = (String) userData.get("picture");
        String auth0Id = (String) userData.get("sub");

        User newUser =new User(email=email, image=image, auth0Id=auth0Id);
        return createUser(newUser);
    }
}
