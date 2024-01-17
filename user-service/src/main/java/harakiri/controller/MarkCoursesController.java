package harakiri.controller;

import harakiri.dto.request.MarkCourseRequest;
import harakiri.security.filter.UserContextHolder;
import harakiri.service.MarkedCoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/markcourse")
public class MarkCoursesController {
   private final MarkedCoursesService markCourse;

   @PostMapping
   public void markCourse(@RequestBody MarkCourseRequest markCourseRequest) {
      markCourse.markCourse(markCourseRequest, UserContextHolder.getId());
   }

   @GetMapping
   public ResponseEntity<?> getMarkedCourses() {
      var res = markCourse.getMarkedCourse(UserContextHolder.getId());

      return new ResponseEntity<>(res, HttpStatusCode.valueOf(200));
   }

   @DeleteMapping("/{id}")
   public void deleteMarkedCourse(@PathVariable String id) {
      markCourse.deleteMarkedCourse(id, UserContextHolder.getId());
   }
}
