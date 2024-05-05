package harakiri.service;

import harakiri.dto.request.Answer;
import harakiri.dto.request.CheckAnswerRequest;
import harakiri.dto.response.TestResultResponse;
import harakiri.model.test.TestCollection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestValidationService {
    private final TestService testService;
    private final TestHistoryService testHistoryService;

    public TestResultResponse valid(CheckAnswerRequest checkAnswerRequest, Long userId, String testId) {
        TestCollection test = testService.getbyId(testId);
        test.getQuestionList().stream().forEach(q -> {

        });
    }


}
