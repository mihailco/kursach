package harakiri.service;

import harakiri.dto.request.MarkCourseRequest;
import harakiri.entity.MarkedCoursesResponse;
import harakiri.entity.UserCourses;
import harakiri.entity.UserEntity;
import harakiri.repository.MarkedCourseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarkedCoursesService {
    @PersistenceContext
    private EntityManager entityManager;
    private final MarkedCourseRepository markedCourseRepository;

    public List<MarkedCoursesResponse> getMarkedCourse(Long userId) {
        var t = markedCourseRepository.findAllByUserId(userId).stream()
                .map(e -> MarkedCoursesResponse.builder()
                        .id(e.getCourseId())
                        .price(e.getPrice())
                        .date(e.getDate())
                        .build())
                .toList();

        return t;
    }

    public void markCourse(MarkCourseRequest markCourseRequest, Long id) {
        var user = entityManager.getReference(UserEntity.class, id);


        UserCourses userCourses = UserCourses.builder().courseId(markCourseRequest.getCourseId()).build();
        markedCourseRepository.save(userCourses);
    }

    public void markCourse(String courseId, Long userId) {
        var user = entityManager.getReference(UserEntity.class, userId);


        UserCourses userCourses = UserCourses.builder()
                .courseId(courseId)
                .date(new Date())
                .user(user)
                .build();
        markedCourseRepository.save(userCourses);
    }

    public void deleteMarkedCourse(String courseId, Long id) {
        markedCourseRepository.deleteAllByCourseIdAndUserId(courseId, id);
    }

    public List<MarkedCoursesResponse> getAll() {
        return markedCourseRepository.findAll().stream()
                .map(e -> MarkedCoursesResponse.builder()
                        .id(e.getCourseId())
                        .price(e.getPrice())
                        .date(e.getDate())
                        .build())
                .toList();
    }
}
