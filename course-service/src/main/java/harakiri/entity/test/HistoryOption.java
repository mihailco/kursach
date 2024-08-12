package harakiri.entity.test;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HistoryOption{
   private String questionId;
   private boolean isCorrect;
}
