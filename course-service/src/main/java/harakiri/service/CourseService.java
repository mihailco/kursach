package harakiri.service;

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

    public List<CourseCollection> getAllCourseInfo() {
        return courseRepository.findAllCourseInfo();
    }

    public void deleteCourse(String id) {
        courseRepository.deleteById(new ObjectId(id));
    }
}
