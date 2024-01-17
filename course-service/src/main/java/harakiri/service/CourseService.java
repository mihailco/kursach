package harakiri.service;

import harakiri.dto.request.SearchCourseRequest;
import harakiri.dto.response.CourseInfoResponse;
import harakiri.model.CourseCollection;
import harakiri.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
   private final CourseRepository courseRepository;

   public CourseCollection saveCourse(CourseCollection course) {
      return courseRepository.save(course);
   }

   public CourseCollection getCourseById(String id) {
      return courseRepository.findById(new ObjectId(id)).orElse(null);
   }

   public List<CourseInfoResponse> getAllCourseInfo() {
      var cs = courseRepository.findAllCourseInfo();
      return cs.stream().map(this::courseToInfo).toList();
   }

   private CourseInfoResponse courseToInfo(CourseCollection course) {
      return CourseInfoResponse.builder()
//              .creatorId(course.getCreator().getId())
              .FIO(course.getTittlePage().getFio())
              .tittle(course.getTittlePage().getTittle())
              .createdAt(course.getCreatedAt())
              .id(course.getId().toString())
              .courseFor(course.getSecondPage().getThisCourseFor())
              .learningResults(course.getLearningResults())
              .build();
   }

   public void deleteCourse(String id) {
      courseRepository.deleteById(new ObjectId(id));
   }

   public List<CourseInfoResponse> searchCourses(SearchCourseRequest searchRequest) {
      var cs = courseRepository.search(searchRequest);
      return cs.stream().map(this::courseToInfo).toList();
   }

   public CourseInfoResponse getCourseInfoById(String id) {
      var t = courseRepository.findById(new ObjectId(id)).orElse(null);
      return courseToInfo(t);
   }
}
