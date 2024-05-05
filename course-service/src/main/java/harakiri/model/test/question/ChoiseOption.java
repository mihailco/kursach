package harakiri.model.test.question;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ChoiseOption {
    @Id
    private String id;
    private String text;
    private boolean isCorrect;
}
