package harakiri.model.test;

import jakarta.persistence.Id;
import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Builder
@Document(collection = "user-session")
public class UserSessionCollection {
    @Id
    private String id;
    private String userId;
    private String testId;
    private Date date;
    private int minutesDuration;
}

