package harakiri.service.validator;

import harakiri.entity.test.question.ChoiseOption;
import harakiri.entity.test.question.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class MultipleChooseValidator extends AnswerValidator {
    @Override
    public boolean validateAnswer(Question answer, Question question) {
        List<ChoiseOption> userChoices = answer.getChooseOption();
        List<ChoiseOption> correctChoices = question.getChooseOption();
        var correctMap = correctChoices.stream()
                .collect(Collectors.toMap(ChoiseOption::getText, ChoiseOption::isCorrect));

        AtomicBoolean isCorrect = new AtomicBoolean(true);
        userChoices.forEach(userChoice -> {
            if(!correctMap.get(userChoice.getText()).equals(userChoice.isCorrect()))
                isCorrect.set(false);
        });

        return isCorrect.get();
    }
}
