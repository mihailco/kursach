package harakiri.repository.Impl;

import harakiri.model.CourseCollection;
import harakiri.repository.CourseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao {
    @Override
    public List<CourseCollection> findAllCoursesInfo() {
        return null;
    }
}
