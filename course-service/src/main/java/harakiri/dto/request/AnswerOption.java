package harakiri.dto.request;

import lombok.Data;

@Data
public class AnswerOption{
    private String id;
    private String answer;
    private String juxtapositionText;
    private int n;
}
