package harakiri.controller;

import harakiri.model.test.TestCollection;
import harakiri.model.test.TestHistoryCollection;
import harakiri.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course/test")
public class TestController {

    private final TestService testService;

    @PostMapping
    public ResponseEntity<?> createTest(@RequestBody TestCollection collection) {
        testService.save(collection);
        return ResponseEntity.ok("created");
    }

    @GetMapping("/{id}")
    public TestCollection getTestById(@PathVariable String id){
        return testService.getbyId(id);
    }

    @DeleteMapping("/{id]")
    public void deleteTestById(@PathVariable String id){
        testService.deleteById(id);
    }
}
