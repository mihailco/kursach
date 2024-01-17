package harakiri.controller;

import harakiri.dto.response.UserResponse;
import harakiri.entity.UserEntity;
import harakiri.mapper.BasicMapper;
import harakiri.security.filter.UserContextHolder;
import harakiri.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
   private final UserService userService;
   private final BasicMapper basicMapper;

   @GetMapping("/self")
   public UserResponse loadSelf() {
      return basicMapper.convertTo(userService.getById(UserContextHolder.getId()), UserResponse.class);
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

   @PutMapping("/{id}")
   public UserEntity updateUser(@RequestBody UserEntity user, @PathVariable int id) {
      user.setId(id);
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





//   @PutMapping("/teacher/{id}")
//   public void becomeTeacher(@PathVariable int id){
//      userService.becameTeacher(id);
//   }

}
