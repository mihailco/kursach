package harakiri.controller;

import harakiri.dto.request.MarkCourseRequest;
import harakiri.entity.MarkedCoursesResponse;
import harakiri.security.filter.UserContextHolder;
import harakiri.service.MarkedCoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<MarkedCoursesResponse>> getMarkedCourses() {
        var t = ResponseEntity.ok(markCourse.getMarkedCourse(UserContextHolder.getId()));
        return t;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<MarkedCoursesResponse>> getMarkedCoursesByUserId(@PathVariable long id) {
        return ResponseEntity.ok(markCourse.getMarkedCourse(id));
    }


    @GetMapping("/all")
    public ResponseEntity<List<MarkedCoursesResponse> >getAllMarkedCourses() {
        return ResponseEntity.ok( markCourse.getAll());
    }

    @DeleteMapping("/{id}")
    public void deleteMarkedCourse(@PathVariable String id) {
        markCourse.deleteMarkedCourse(id, UserContextHolder.getId());
    }
}
