package edu.sjsu.cmpe272.simpleblog.server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    @Autowired
    private UserData UserData;

    public User saveUser(User user) {
        return UserData.save(user);
    }

    public String getPublicKeyByUsername(String username) {
        return UserData.findById(username)
                .map(User::getPublicKey)
                .orElseThrow(() ->  new RuntimeException("User with username " + username + " not found"));
    }
}