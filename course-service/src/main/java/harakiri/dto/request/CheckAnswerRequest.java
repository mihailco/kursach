package harakiri.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CheckAnswerRequest {
    private String testId;
    private List<Answer> answerList;
}

