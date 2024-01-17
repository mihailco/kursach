package harakiri.service;


import harakiri.dto.request.MarkCourseRequest;
import harakiri.entity.UserEntity;
import harakiri.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        var bdUser = userRepository.getById(user.getId());
        bdUser.setFio(user.getFio());
        bdUser.setDescription(user.getDescription());
        bdUser.setUsername(user.getUsername());
        return userRepository.save(bdUser);
    }


    public UserEntity getByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public UserEntity getById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public void markCourse(MarkCourseRequest markCourseRequest, Long id) {
    }
}
