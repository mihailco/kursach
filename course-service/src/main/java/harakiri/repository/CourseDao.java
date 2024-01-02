package harakiri.repository;

import harakiri.model.CourseCollection;

import java.util.List;

public interface CourseDao {
    List<CourseCollection> findAllCoursesInfo();
}
