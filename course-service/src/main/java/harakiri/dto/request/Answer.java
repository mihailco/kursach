package harakiri.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class Answer {
    private String questionId;
    private List<AnswerOption> answer;
}
