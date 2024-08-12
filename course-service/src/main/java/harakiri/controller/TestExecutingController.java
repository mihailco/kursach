package harakiri.controller;

import harakiri.dto.request.CheckAnswerRequest;
import harakiri.dto.response.BeginTestResponse;
import harakiri.dto.response.TestInfo;
import harakiri.entity.test.TestCollection;
import harakiri.entity.test.TestHistoryCollection;
import harakiri.exceptions.AccessDeniedException;
import harakiri.security.filter.UserContextHolder;
import harakiri.service.TestService;
import harakiri.service.TestValidationService;
import harakiri.service.UserSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course/test-exec")
public class TestExecutingController {
   private final TestService testService;
   private final UserSessionService userSessionService;
   private final TestValidationService testValidationService;

   @GetMapping("/begin/{testId}")
   public BeginTestResponse beginTest(@PathVariable String testId) throws AccessDeniedException {
      return userSessionService.beginTest(testId, UserContextHolder.getId());
   }

   @GetMapping("/info/{testId}")
   public TestCollection getTestinfo(@PathVariable String testId) throws AccessDeniedException {
      return userSessionService.getTestInfo(testId, UserContextHolder.getId());
   }

   @PostMapping("/submit")
   public TestHistoryCollection submitTest(@RequestBody TestCollection testCollection) {
      var t = testValidationService.valid(testCollection, UserContextHolder.getId());
      userSessionService.deleteSession(testCollection.getId(), String.valueOf(UserContextHolder.getId()));
      return t;
   }
}


