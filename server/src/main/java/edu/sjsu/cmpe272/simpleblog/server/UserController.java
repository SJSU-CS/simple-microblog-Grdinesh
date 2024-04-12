package edu.sjsu.cmpe272.simpleblog.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        System.out.println("Creating user with username: " + user.getUsername());
        userService.saveUser(user);
        return ResponseEntity.ok("Welcome "+user.getUsername());
    }

    @GetMapping("/{username}/public-key")
    public ResponseEntity<String> getPublicKey(@PathVariable String username) {
        String publicKey = userService.getPublicKeyByUsername(username);
        return ResponseEntity.ok(publicKey);
    }
}
