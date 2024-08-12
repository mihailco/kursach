package harakiri.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ExecuteCodeResponse {
   private boolean isCorrect;
   private String result;
}
