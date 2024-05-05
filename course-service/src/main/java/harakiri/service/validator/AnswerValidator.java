package harakiri.service.validator;

import harakiri.dto.request.Answer;
import harakiri.model.test.question.Question;

public abstract class AnswerValidator {
    public  abstract boolean validateAnswer(Answer answer, Question question);
}