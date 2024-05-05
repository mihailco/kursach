package harakiri.service.validator;

import harakiri.dto.request.Answer;
import harakiri.model.test.question.ChoiseOption;
import harakiri.model.test.question.Question;

import java.util.List;
import java.util.Objects;

public class SingleChioseValidatior extends AnswerValidator {
    @Override
    public boolean validateAnswer(Answer answer, Question question) {
        if (answer.getAnswer().size() != 1) {
            return false;
        }
        String id = answer.getAnswer().get(0).getId();
        ChoiseOption foundById = question.getChooseOption().stream().filter(x -> Objects.equals(x.getId(), id)).findFirst().get();
        return foundById.isCorrect();
    }
}
