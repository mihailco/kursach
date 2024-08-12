package harakiri.entity.test;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@Document(collection = "user-session")
public class UserSessionCollection {
    @Id
    private String id;
    private String userId;
    private String testId;
    private Date date;
    private int minutesDuration;
    private TestCollection testCollection;
}

