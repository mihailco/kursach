package harakiri.controller;

import harakiri.security.filter.UserContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import harakiri.entity.UserEntity;
import harakiri.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/self")
    public UserEntity loadSelf(){
        return userService.getById(UserContextHolder.getId());
    }


    @PostMapping
    public UserEntity registerUser(@RequestBody UserEntity user) {
        return userService.register(user);
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable long id) {
        return userService.findById(id);
    }

    @PutMapping
    public UserEntity updateUser(@RequestBody UserEntity user) {
        return userService.updateUser(user);
    }

    @GetMapping("/username/{username}")
    public UserEntity getUserByUsername(@PathVariable String username) {
        return userService.getByUsername(username);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

}
