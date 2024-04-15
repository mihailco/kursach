package harakiri.service;

import harakiri.dto.request.CreateCourseRequest;
import harakiri.dto.request.SearchCourseRequest;
import harakiri.kafka.messages.UserMessage;
import harakiri.model.CourseCollection;
import harakiri.repository.CourseRepository;
import harakiri.entity.CourseInfoResponse;
import harakiri.entity.MarkedCoursesResponse;
import harakiri.exceptions.AccessDeniedException;
import harakiri.kafka.Topics;
import harakiri.kafka.events.BoughtCourseEvent;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserServiceRemote userServiceRemote;
    private final KafkaTemplate<String, BoughtCourseEvent> boughtCourseEventKafkaTemplate;

    public CourseCollection saveCourse(CourseCollection course) {
        return courseRepository.save(course);
    }

    public CourseCollection getCourseById(String id) {
        return courseRepository.findById(new ObjectId(id)).orElse(null);
    }

    public List<CourseInfoResponse> getAllCourseInfo() {
        var cs = courseRepository.findAllCourseInfo();
        return cs.stream().map(this::courseToInfo).toList();
    }

    private CourseInfoResponse courseToInfo(CourseCollection course) {
        return CourseInfoResponse.builder()
                .creatorId(String.valueOf(course.getCreatorId()))
                .FIO(course.getFIO())
                .tittle(course.getTittle())
                .createdAt(course.getCreatedAt())
                .id(course.getId())
                .courseFor(course.getCourseFor())
                .learningResults(course.getLearningResults())
                .price(course.getPrice())
                .build();
    }

    public void deleteCourse(String id) {
        courseRepository.deleteById(new ObjectId(id));
    }

    public List<CourseInfoResponse> searchCourses(SearchCourseRequest searchRequest) {
        var cs = courseRepository.search(searchRequest);
        return cs.stream().map(this::courseToInfo).toList();
    }

    public CourseInfoResponse getCourseInfoById(String id) {
        var t = courseRepository.findById(new ObjectId(id)).orElse(null);
        assert t != null;
        return courseToInfo(t);
    }

    public CourseCollection createCourse(CreateCourseRequest createCourseRequest) {
        CourseCollection courseCollection = CourseCollection.builder()
                .tittle(createCourseRequest.getTittle())
                .courseFor(createCourseRequest.getCourseFor())
                .learningResults(createCourseRequest.getWhatLearning())
                .createdAt(new Date())
                .FIO(createCourseRequest.getFio())
                .build();

        return courseRepository.save(courseCollection);
    }

    public void buyCourse(String courseId, Long userId) throws AccessDeniedException {
        UserMessage user = userServiceRemote.getUserById(userId).orElseThrow(() ->
                new AccessDeniedException("")
        );

        CourseInfoResponse courseInfo = getCourseInfoById(courseId);

        BoughtCourseEvent event = BoughtCourseEvent.builder()
                .userId(userId)
                .fio(courseInfo.getFIO())
                .price(String.valueOf(courseInfo.getPrice()))
                .email(user.getUsername())
                .courseTittle(courseInfo.getTittle())
                .courseCreator(courseInfo.getFIO())
                .courseId(courseId)
                .build();

        boughtCourseEventKafkaTemplate.send(Topics.COURSE_SERVICE_PURCHASE_EVENTS, event);
    }

    public List<CourseInfoResponse> searchCourses(MarkedCoursesResponse[] searchRequest) {
        var t = courseRepository.findAllById(Arrays.stream(searchRequest).map(e -> new ObjectId(e.getId())).collect(Collectors.toList()));
        return t.stream().map(this::courseToInfo).collect(Collectors.toList());
    }
}
