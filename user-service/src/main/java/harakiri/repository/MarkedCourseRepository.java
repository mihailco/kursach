package harakiri.repository;

import harakiri.entity.UserCourses;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkedCourseRepository extends JpaRepository<UserCourses, Long> {

   @Transactional
   void deleteAllByCourseIdAndUserId(String courseId, long user_id);

   List<UserCourses> findAllByUserId(long userId);
}
