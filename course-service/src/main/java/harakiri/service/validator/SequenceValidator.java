package harakiri.service.validator;

import harakiri.dto.request.Answer;
import harakiri.dto.request.AnswerOption;
import harakiri.entity.test.question.ChoiseOption;
import harakiri.entity.test.question.Question;
import harakiri.entity.test.question.SequenceOption;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SequenceValidator extends AnswerValidator {
    @Override
    public boolean validateAnswer(Question answer, Question question) {
        List<SequenceOption> userSequence = answer.getSequenceOptions();
        List<SequenceOption> correctSequence = question.getSequenceOptions();

        if (userSequence.size() != correctSequence.size()) {
            return false;
        }

        for (int i = 0; i < correctSequence.size(); i++) {
            if (!userSequence.get(i).getText().equals(correctSequence.get(i).getText())) {
                return false;
            }
        }

        return true;
    }
}
