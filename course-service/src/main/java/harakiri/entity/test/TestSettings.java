package harakiri.entity.test;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TestSettings {
   private int durationMinutes;
   private int nQuestions = -1;
   private int nTries;
   private boolean isMixed;
}
