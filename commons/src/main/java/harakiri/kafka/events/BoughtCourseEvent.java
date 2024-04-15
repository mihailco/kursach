package harakiri.kafka.events;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoughtCourseEvent {
    private long userId;
    private String fio;
    private String email;

    private String courseTittle;
    private String courseCreator;
    private String courseId;
    private String price;
}
