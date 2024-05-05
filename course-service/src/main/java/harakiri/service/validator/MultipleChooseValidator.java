package harakiri.service.validator;

import harakiri.dto.request.Answer;
import harakiri.dto.request.AnswerOption;
import harakiri.model.test.question.ChoiseOption;
import harakiri.model.test.question.Question;
import lombok.Data;

import java.util.List;
import java.util.Objects;

public class MultipleChooseValidator extends AnswerValidator {
    @Override
    public boolean validateAnswer(Answer answer, Question question) {
        List<AnswerOption> ans = answer.getAnswer();
        List<ChoiseOption> ansvOption = question.getChooseOption();

        for (ChoiseOption e : ansvOption) {
            boolean contains = ans.stream().anyMatch(s -> Objects.equals(s.getId(), e.getId()));
            if (!contains) return false;
        }

        return true;
    }
}
