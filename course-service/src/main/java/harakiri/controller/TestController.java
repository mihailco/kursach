package harakiri.controller;

import harakiri.entity.test.TestCollection;
import harakiri.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course/test")
public class TestController {

   private final TestService testService;

   @PostMapping
   public TestCollection createTest(@RequestBody TestCollection collection) {
      return testService.save(collection);
   }

   @GetMapping("/{id}")
   public TestCollection getTestById(@PathVariable String id) {
      return testService.getbyId(id);
   }

   @DeleteMapping("/{id}")
   public void deleteTestById(@PathVariable String id) {
      testService.deleteById(id);
   }
}
