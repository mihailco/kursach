package harakiri.service.validator;

import harakiri.dto.request.Answer;
import harakiri.dto.request.AnswerOption;
import harakiri.entity.test.question.ChoiseOption;
import harakiri.entity.test.question.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MultipleChooseValidator extends AnswerValidator {
    @Override
    public boolean validateAnswer(Question answer, Question question) {
        List<ChoiseOption> userChoices = answer.getChooseOption();
        List<ChoiseOption> correctChoices = question.getChooseOption();

        // Проверяем, что все правильные опции выбраны и что выбраны только правильные опции
        for (ChoiseOption correctChoice : correctChoices) {
            boolean isChosenCorrectly = userChoices.stream()
                    .anyMatch(userChoice -> Objects.equals(userChoice.getId(),
                            correctChoice.getId()) && userChoice.isCorrect());

            if (!isChosenCorrectly) {
                return false;
            }
        }

        // Проверяем, что не выбраны неправильные опции
        for (ChoiseOption userChoice : userChoices) {
            boolean isCorrectOption = correctChoices.stream()
                    .anyMatch(correctChoice -> Objects.equals(correctChoice.getId(),
                            userChoice.getId()) && correctChoice.isCorrect());

            if (!isCorrectOption) {
                return false;
            }
        }

        return true;
    }
}
