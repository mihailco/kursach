package harakiri.kafka.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GreetingEmailEvent {
    private long id;
    private String fio;
    private String email;
}
