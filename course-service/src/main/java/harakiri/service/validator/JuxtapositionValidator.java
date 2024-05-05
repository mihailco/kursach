package harakiri.service.validator;

import harakiri.dto.request.Answer;
import harakiri.dto.request.AnswerOption;
import harakiri.model.test.question.ChoiseOption;
import harakiri.model.test.question.Question;

import java.util.List;
import java.util.Objects;

public class JuxtapositionValidator extends AnswerValidator {

    @Override
    public boolean validateAnswer(Answer answer, Question question) {
        List<AnswerOption> ans = answer.getAnswer();
        var jo = question.getJuxtapositionOption();
        int ind;
        for (int i = 0; i < ans.size(); i++) {
            ind = jo.getLeftOption().indexOf(ans.get(i).getAnswer());
            if (ind == -1) {
                return false;
            }
            if (!Objects.equals(jo.getRightOption().get(ind), ans.get(i).getAnswer())) {
                return false;
            }
        }

        return true;
    }
}
