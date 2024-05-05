package harakiri.service.validator;

import harakiri.dto.request.Answer;
import harakiri.dto.request.AnswerOption;
import harakiri.model.test.question.ChoiseOption;
import harakiri.model.test.question.Question;

import java.util.List;
import java.util.Objects;

public class SequenceValidator extends AnswerValidator {
    @Override
    public boolean validateAnswer(Answer answer, Question question) {
        List<AnswerOption> ans = answer.getAnswer();
        List<ChoiseOption> ansvOption = question.getChooseOption();

        for (int i = 0; i < ansvOption.size(); i++) {
            if (!Objects.equals(ansvOption.get(i).getId(), ans.get(i).getId())) {
                return false;
            }
        }

        return true;
    }
}
