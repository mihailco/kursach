package harakiri.service;

import harakiri.dto.response.BeginTestResponse;
import harakiri.model.test.TestCollection;
import harakiri.model.test.UserSessionCollection;
import harakiri.repository.UserSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSessionService {
    private final UserSessionRepository userSessionRepository;
    private final TestService testService;

    public BeginTestResponse beginTest(String testId, Long userId) {
        TestCollection testCollection = testService.getbyId(testId);

        UserSessionCollection u = userSessionRepository.findOneByTestIdAndUserId(testId, String.valueOf(userId));
        if (u == null) {

            u = UserSessionCollection.builder()
                    .userId(String.valueOf(userId))
                    .testId(testId)
                    .date(new Date())
                    .minutesDuration(testCollection.getTestSettings().getDurationMinutes())
                    .build();
            userSessionRepository.save(u);
        }

        return BeginTestResponse.builder()
                .userSession(u)
                .test(testCollection)
                .build();
    }
}
