package harakiri.service;

import harakiri.entity.CourseInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseServiceRemote {
    private final RestTemplate restTemplate;


    public CourseInfoResponse[] getMarkedCourses(List<String> ids) {
        String url = "http://course-service//api/v1/course/report";
        return restTemplate.postForObject(url, ids, CourseInfoResponse[].class);
    }

    public CourseInfoResponse getCoursesById(String id) {
        String url = "http://course-service//api/v1/course/info/" + id;
        return restTemplate.getForObject(url, CourseInfoResponse.class);
    }

    public CourseInfoResponse[] getCourses() {
        String url = "http://course-service//api/v1/course/all";
        return restTemplate.getForObject(url, CourseInfoResponse[].class);
    }
}
