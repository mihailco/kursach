package harakiri.service;

import harakiri.dto.request.ExecuteCodeRequest;
import harakiri.dto.response.ExecuteCodeResponse;
import harakiri.entity.test.TestCollection;
import harakiri.entity.test.question.Question;
import harakiri.service.validator.CodeValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CodeExecutorService {
   private final TestService testService;
   private final CodeValidatorService codeValidatorService;

   public ExecuteCodeResponse execute(ExecuteCodeRequest executeCodeRequest) {
      TestCollection test = testService.getbyId(executeCodeRequest.getTestId());

      Question question = test.getQuestionList().stream()
              .filter(e -> Objects.equals(e.getId(), executeCodeRequest.getQuestionId())).findFirst().orElse(null);

      assert question != null;

      return CodeValidatorService.checkPythonFunction(executeCodeRequest.getCode(),
              question.getCodeOption().getFunctionName(),
              question.getCodeOption().getCodeTestCases());
   }
}
