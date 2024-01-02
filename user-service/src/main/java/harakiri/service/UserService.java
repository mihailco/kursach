package harakiri.service;


import lombok.RequiredArgsConstructor;
import harakiri.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import harakiri.entity.UserEntity;
import harakiri.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    final private UserRepository userRepository;

    public UserEntity register(UserEntity user) {
        return userRepository.save(user);
    }

    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    public UserEntity findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity updateUser(UserEntity user) {
        if (userRepository.existsById(user.getId())) {
            throw new NotFoundException("user not found");
        }
        return userRepository.save(user);
    }


    public UserEntity getByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public UserEntity getById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
}
