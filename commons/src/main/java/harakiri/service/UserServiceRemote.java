package harakiri.service;

import harakiri.entity.MarkedCoursesResponse;
import harakiri.kafka.messages.UserMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserServiceRemote {

    private final RestTemplate restTemplate;


    public Optional<UserMessage> getUserById(long id) {
        try {
            String url = String.format("http://user-service//api/v1/user/%s", id);

            UserMessage userMessage = restTemplate.getForObject(url, UserMessage.class);

            assert userMessage != null;
            return Optional.of(userMessage);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public MarkedCoursesResponse[] getAllMarkedCourses() {
        String url = "http://user-service//api/v1/user/markcourse/all";
        return restTemplate.getForObject(url, MarkedCoursesResponse[].class);
    }

    public MarkedCoursesResponse[] getMarkedCourses(long id) {
        String url = "http://user-service//api/v1/user/markcourse/" + id;
        return restTemplate.getForObject(url, MarkedCoursesResponse[].class);
    }
}
