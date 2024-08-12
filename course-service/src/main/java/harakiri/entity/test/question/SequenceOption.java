package harakiri.entity.test.question;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class SequenceOption {
    private String id = new ObjectId().toString();
    private String text;
    private int n;
}
