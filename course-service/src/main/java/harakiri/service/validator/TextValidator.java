package harakiri.service.validator;

import harakiri.dto.request.Answer;
import harakiri.dto.request.AnswerOption;
import harakiri.model.test.question.Question;

import java.util.List;

public class TextValidator extends AnswerValidator{
    @Override
    public boolean validateAnswer(Answer answer, Question question) {
        String ans = answer.getAnswer().get(0).getAnswer();
        return question.getCorrectText().equals(ans);
    }
}
