package harakiri.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class TestResultResponse {
   List<QuestionResult> results;
   private float mark;
}
