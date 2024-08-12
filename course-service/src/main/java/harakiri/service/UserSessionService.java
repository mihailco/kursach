package harakiri.service;

import harakiri.dto.response.BeginTestResponse;
import harakiri.entity.test.TestCollection;
import harakiri.entity.test.TestSettings;
import harakiri.entity.test.UserSessionCollection;
import harakiri.exceptions.AccessDeniedException;
import harakiri.repository.UserSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserSessionService {
   private final UserSessionRepository userSessionRepository;
   private final TestService testService;

   public BeginTestResponse beginTest(String testId, Long userId) throws AccessDeniedException {


      TestCollection testCollection;
      UserSessionCollection u = userSessionRepository.findOneByTestIdAndUserId(testId, String.valueOf(userId));

      if (u == null || u.getTestCollection() == null) {
         testCollection = testService.getTestInfo(testId, String.valueOf(userId));
         Collections.shuffle(testCollection.getQuestionList());

         u = UserSessionCollection.builder()
                 .userId(String.valueOf(userId))
                 .testId(testId)
                 .date(new Date())
                 .minutesDuration(testCollection.getTestSettings().getDurationMinutes())
                 .testCollection(testCollection)
                 .build();

         userSessionRepository.save(u);

      }
      testCollection = u.getTestCollection();

      TestSettings settings = testCollection.getTestSettings();

      if (testCollection.getTestSettings().getNTries() > 0) {
         int remainingTries = remainingNumberOfAttempts(testCollection);
         if (remainingTries <= 0) {
            throw new AccessDeniedException("No more attempts");
         }
         settings.setNTries(remainingTries);
      }


      if (settings.getNQuestions() != -1) {
         testCollection.setQuestionList(testCollection.getQuestionList().subList(0, settings.getNQuestions()));
      }

      return BeginTestResponse.builder()
              .userSession(u)
              .test(testCollection)
              .build();
   }

   public void deleteSession(String testId, String userId){
      userSessionRepository.deleteAllByTestIdAndUserId(testId, userId);
   }
   public TestCollection getTestInfo(String testId, Long id) throws AccessDeniedException {
      TestCollection testCollection = testService.getTestInfo(testId, String.valueOf(id));

      if (testCollection.getTestSettings() != null) {
         if (testCollection.getTestSettings().getNTries() != -1) {
            int remainingTries = remainingNumberOfAttempts(testCollection);
            testCollection.getTestSettings().setNTries(remainingTries);
         }
      }
      return testCollection;
   }

   private int remainingNumberOfAttempts(TestCollection testCollection) {
      int nTries = testCollection.getTestSettings().getNTries();
      if (testCollection.getTestTakings() == null) {
         return nTries;
      }
      return nTries - testCollection.getTestTakings().size();
   }


}
