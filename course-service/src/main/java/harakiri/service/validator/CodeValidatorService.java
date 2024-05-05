package harakiri.service.validator;

import harakiri.dto.request.Answer;
import harakiri.model.test.question.Question;
import org.python.util.PythonInterpreter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Objects;

public class CodeValidatorService extends AnswerValidator {
    @Override
    public boolean validateAnswer(Answer answer, Question question) {
        try (PythonInterpreter pyInterp = new PythonInterpreter()) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintStream stream = new PrintStream(out);
            var opt = question.getCodeOption();
            for (var i : opt.getValidationCode()) {
                pyInterp.exec(answer.getAnswer() + i);
                String output = out.toString();
                if (Objects.equals(output, "false")) {
                    return false;
                }
            }
        }

        return true;
    }
}
