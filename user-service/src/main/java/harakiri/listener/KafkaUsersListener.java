package harakiri.listener;

import harakiri.kafka.KafkaGroupIds;
import harakiri.kafka.Topics;
import harakiri.kafka.events.BoughtCourseEvent;
import harakiri.mapper.BasicMapper;
import harakiri.service.MarkedCoursesService;
import harakiri.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaUsersListener {
    private final UserService userService;
    private final BasicMapper basicMapper;
    private final MarkedCoursesService markedCoursesService;

//    @KafkaListener(id = KafkaGroupIds.USERS, topics = Topics.REQUEST_USER)
//    @SendTo // use default replyTo expression
//    public UserMessage listen(long id) {
//        return basicMapper.convertTo(userService.getById(id), UserMessage.class);
//    }

    @KafkaListener(id = KafkaGroupIds.USERS, topics = Topics.COURSE_SERVICE_PURCHASE_EVENTS)
    public void listen(BoughtCourseEvent boughtCourseEvent) {
        markedCoursesService.markCourse(boughtCourseEvent.getCourseId(), boughtCourseEvent.getUserId());
    }

}
