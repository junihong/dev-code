package me.tamsil.multidatasource.controller;

import lombok.RequiredArgsConstructor;
import me.tamsil.multidatasource.entity.user.User;
import me.tamsil.multidatasource.repository.user.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/findAll")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }
}
