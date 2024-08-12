package harakiri.service;

import harakiri.entity.test.HistoryOption;
import harakiri.entity.test.TestCollection;
import harakiri.entity.test.TestHistoryCollection;
import harakiri.entity.test.TestTaking;
import harakiri.entity.test.question.Question;
import harakiri.service.validator.QuestionValidatorMediator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class TestValidationService {
   private final QuestionValidatorMediator questionValidatorMediator;
   private final TestService testService;
   private final TestHistoryService testHistoryService;

   public TestHistoryCollection valid(TestCollection ans, Long userId) {
      String testId = ans.getId();
      TestCollection test = testService.getbyId(testId);


      AtomicReference<Double> maxResult = new AtomicReference<>((double) 0);
      AtomicReference<Double> resultPoints = new AtomicReference<>((double) 0);
      List<HistoryOption> historyOptionList = new LinkedList<>();

      AtomicInteger correctAnswers = new AtomicInteger();

      ans.getQuestionList().forEach(q -> {
         Question answer = test.getQuestionList().stream()
                 .filter(e -> Objects.equals(e.getId(), q.getId())).findFirst().orElse(null);

         boolean isCorrect = false;
         if (answer != null) {
            isCorrect = questionValidatorMediator.validateAnswer(answer, q);
            var points = answer.getQuestionPoints();
            maxResult.updateAndGet(v -> v + points);
            if (isCorrect) {
               resultPoints.updateAndGet(v -> v + points);
               correctAnswers.getAndIncrement();
            }
         }

         historyOptionList.add(HistoryOption.builder()
                 .isCorrect(isCorrect)
                 .questionId(q.getId())
                 .build());
      });


      var res = TestHistoryCollection.builder()
              .accuracyPercent(resultPoints.get() / maxResult.get())
              .answers(historyOptionList)
              .testCollection(ans)
              .build();

      res = testHistoryService.save(res);

      testService.addTestTaking(TestTaking.builder()
              .date(new Date())
              .res(resultPoints.get() / maxResult.get())
              .userId(String.valueOf(userId))
              .build(), testId);

      return res;
   }


}
