package harakiri.entity.test.question;

import jakarta.persistence.Id;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class ChoiseOption {
    @Id
    private String id = new ObjectId().toString();
    private String text;
    private boolean isCorrect;
}
