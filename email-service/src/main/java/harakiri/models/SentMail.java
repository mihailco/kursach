package harakiri.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "sent-mail")
public class SentMail {
    @Id
    private ObjectId id;
    private String idTopic;
    private String name;
    private List<String> userIdTo;
    private Date sentAt;
    private String text;
}
