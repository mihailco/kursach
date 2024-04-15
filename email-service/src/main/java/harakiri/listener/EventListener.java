package harakiri.listener;

import harakiri.dto.SendMailModelRequest;
import harakiri.service.Impl.BasicMailServiceImpl;
import harakiri.kafka.KafkaGroupIds;
import harakiri.kafka.Topics;
import harakiri.kafka.events.BoughtCourseEvent;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventListener {
    private final BasicMailServiceImpl basicMailServiceImpl;

    @KafkaListener(topics = Topics.COURSE_SERVICE_PURCHASE_EVENTS, groupId = KafkaGroupIds.EMAIL)
    private void onDeleteUser(BoughtCourseEvent event) throws MessagingException {
        log.info("Received: {}", event);
        SendMailModelRequest sendMailModel = SendMailModelRequest.builder()
                .targetEmail(event.getEmail())
                .message(String.format("Вы приобрели курс \"%s\". Автор: %s.     Цена: %s",
                        event.getCourseTittle(),
                        event.getCourseCreator(),
                        event.getPrice()))
                .build();

        basicMailServiceImpl.send(sendMailModel.getTargetEmail(), sendMailModel.getMessage(), "Покупка курса");
    }
}
