package harakiri.repository;

import harakiri.dto.request.SearchCourseRequest;
import harakiri.entity.course.CourseCollection;

import java.util.List;

public interface CourseDao {
   List<CourseCollection> search(SearchCourseRequest searchRequest);
}
