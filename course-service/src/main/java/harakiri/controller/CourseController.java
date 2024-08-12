package harakiri.controller;

import harakiri.dto.request.SearchCourseRequest;
import harakiri.dto.request.CreateCourseRequest;
import harakiri.entity.CourseInfoResponse;
import harakiri.entity.MarkedCoursesResponse;
import harakiri.exceptions.AccessDeniedException;
import harakiri.entity.course.CourseCollection;
import harakiri.security.filter.UserContextHolder;
import harakiri.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course")
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/buy/{courseId}")
    public void buyCourse(@PathVariable String courseId) throws AccessDeniedException {
        courseService.buyCourse(courseId, UserContextHolder.getId());
    }

    @PostMapping("/create")
    public ResponseEntity<CourseCollection> createCourse(@RequestBody CreateCourseRequest createCourseRequest) {

        var savedCourse = courseService.createCourse(createCourseRequest);

        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);

    }

    @PostMapping("/full")
    public ResponseEntity<CourseCollection> saveCourse(@RequestBody CourseCollection course) {
        CourseCollection savedCourse = courseService.saveCourse(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<?> getCourseInfoById(@PathVariable String id) {
        return ResponseEntity.ok(courseService.getCourseInfoById(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseCollection> getCourseById(@PathVariable String id) {
        CourseCollection course = courseService.getCourseById(id);
        if (course != null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourseInfoResponse>> getAllCourseInfo() {
        List<CourseInfoResponse> courses = courseService.getAllCourseInfo();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CourseInfoResponse>> searchCourses(@ModelAttribute SearchCourseRequest searchRequest) {
        List<CourseInfoResponse> searchResults = courseService.searchCourses(searchRequest);
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }

    ///ищет курсы по их айдишникам
    @PostMapping("/report")
    public ResponseEntity<List<CourseInfoResponse>> reportCourses(@RequestBody MarkedCoursesResponse[] searchRequest) {
        List<CourseInfoResponse> searchResults = courseService.searchCourses(searchRequest);
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }
}
