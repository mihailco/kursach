package harakiri.service.validator;

import harakiri.dto.request.Answer;
import harakiri.entity.test.question.Question;

public abstract class AnswerValidator {
    public  abstract boolean validateAnswer(Question answer, Question question);
}