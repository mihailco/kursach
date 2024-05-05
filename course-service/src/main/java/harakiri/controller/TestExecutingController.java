package harakiri.controller;

import harakiri.dto.request.CheckAnswerRequest;
import harakiri.dto.response.BeginTestResponse;
import harakiri.dto.response.TestResultResponse;
import harakiri.model.test.TestCollection;
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

    @PostMapping("/begin/{testId}")
    public BeginTestResponse beginTest(@PathVariable String testId) {
        return userSessionService.beginTest(testId, UserContextHolder.getId());
    }

    @PostMapping("/validate/{testId}")
    public TestResultResponse validateTest(@RequestBody CheckAnswerRequest checkAnswerRequest, @PathVariable String testId){
        return testValidationService.valid(checkAnswerRequest, UserContextHolder.getId(), testId);
    }

}
