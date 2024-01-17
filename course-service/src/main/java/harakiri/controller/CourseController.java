package harakiri.controller;

import harakiri.dto.request.SearchCourseRequest;
import harakiri.dto.response.CourseInfoResponse;
import harakiri.model.CourseCollection;
import harakiri.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/course")
public class CourseController {
   private final CourseService courseService;

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
}
