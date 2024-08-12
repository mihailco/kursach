package harakiri.service.validator;

import harakiri.dto.request.Answer;
import harakiri.dto.request.AnswerOption;
import harakiri.entity.test.question.JuxtapositionOption;
import harakiri.entity.test.question.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class JuxtapositionValidator extends AnswerValidator {
    @Override
    public boolean validateAnswer(Question answer, Question question) {
        JuxtapositionOption userOption = answer.getJuxtapositionOption();
        JuxtapositionOption correctOption = question.getJuxtapositionOption();

        if (userOption.getLeftOption().size() != correctOption.getLeftOption().size() ||
                userOption.getRightOption().size() != correctOption.getRightOption().size()) {
            return false;
        }

        for (int i = 0; i < correctOption.getLeftOption().size(); i++) {
            if (!userOption.getLeftOption().get(i).equals(correctOption.getLeftOption().get(i)) ||
                    !userOption.getRightOption().get(i).equals(correctOption.getRightOption().get(i))) {
                return false;
            }
        }

        return true;
    }
}
