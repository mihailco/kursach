package harakiri.service.validator;

import harakiri.dto.request.Answer;
import harakiri.entity.test.question.Question;
import org.springframework.stereotype.Service;

@Service
public class TextValidator extends AnswerValidator {
   @Override
   public boolean validateAnswer(Question answer, Question question) {
      String userText = answer.getCorrectText();
      String correctText = question.getCorrectText();

      return correctText != null && correctText.equals(userText);
   }
}
