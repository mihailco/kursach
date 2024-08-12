package harakiri.entity.test.question;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CodeTestCase {
   private Object[] args;
   private Object expectedResult;
}
