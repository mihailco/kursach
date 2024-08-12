package harakiri.service.validator;

import harakiri.dto.request.Answer;
import harakiri.entity.test.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionValidatorMediator extends AnswerValidator {
   private final CodeValidatorService codeValidatorService;
   private final JuxtapositionValidator juxtapositionValidator;
   private final MultipleChooseValidator multipleChooseValidator;
   private final SequenceValidator sequenceValidator;
   private final TextValidator textValidator;

   @Override
   public boolean validateAnswer(Question answer, Question question) {
      AnswerValidator answerValidator = switch (question.getQuestionType()) {
         case SINGLE_CHOISE, MULTIPLE_CHOICE -> multipleChooseValidator;
         case SEQUENSE -> sequenceValidator;
         case JUXTAPOSITION -> juxtapositionValidator;
         case TEXT -> textValidator;
         case CODE -> codeValidatorService;
      };
      return answerValidator.validateAnswer(answer, question);
   }
}
