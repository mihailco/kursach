package harakiri.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CheckAnswerRequest {
    List<Answer> answerList;
}

