package harakiri.kafka.messages;

import harakiri.entity.UserType;
import lombok.Data;

@Data
public class UserMessage {
    private long id;
    private String fio;
    private String description;
    private String username;
    private UserType userType;
}
