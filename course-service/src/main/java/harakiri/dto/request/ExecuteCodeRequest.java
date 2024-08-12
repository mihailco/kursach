package harakiri.dto.request;

import lombok.Data;

@Data
public class ExecuteCodeRequest {
   private String code;
   private String testId;
   private String questionId;
}
