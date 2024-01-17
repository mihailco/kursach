package harakiri.service;

import harakiri.dto.request.MarkCourseRequest;
import harakiri.dto.response.MarkedCoursesResponse;
import harakiri.entity.UserCourses;
import harakiri.entity.UserEntity;
import harakiri.repository.MarkedCourseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarkedCoursesService {
   @PersistenceContext
   private EntityManager entityManager;
   private final MarkedCourseRepository markedCourseRepository;

   public List<MarkedCoursesResponse> getMarkedCourse(Long userId) {
      List<MarkedCoursesResponse> res = markedCourseRepository.findAllByUserId(userId)
              .stream().map(e -> new MarkedCoursesResponse(e.getCourseId())).toList();
      return res;
   }

   public void markCourse(MarkCourseRequest markCourseRequest, Long id) {
      var user = entityManager.getReference(UserEntity.class, id);
      markedCourseRepository.save(new UserCourses(markCourseRequest.getCourseId(), user));
   }

   public void deleteMarkedCourse(String courseId, Long id) {
      markedCourseRepository.deleteAllByCourseIdAndUserId(courseId, id);
   }
}
