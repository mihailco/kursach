package harakiri.service.validator;

import harakiri.dto.request.Answer;
import harakiri.dto.request.ExecuteCodeRequest;
import harakiri.dto.response.ExecuteCodeResponse;
import harakiri.entity.test.question.CodeTestCase;
import harakiri.entity.test.question.Question;
import org.apache.xmlbeans.impl.xb.ltgfmt.TestCase;
import org.python.core.Py;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class CodeValidatorService extends AnswerValidator {
   @Override
   public boolean validateAnswer(Question answer, Question question) {
      var opt = question.getCodeOption();

      ExecuteCodeResponse response = checkPythonFunction(
              answer.getCodeOption().getDefaultCode(),
              opt.getFunctionName(),
              opt.getCodeTestCases());

      return response.isCorrect();
   }


   public static ExecuteCodeResponse checkPythonFunction(String pythonCode, String functionName, List<CodeTestCase> testCases) {
      try (PythonInterpreter interpreter = new PythonInterpreter()) {

         interpreter.exec(pythonCode);

         PyObject function = interpreter.get(functionName);

         for (CodeTestCase testCase : testCases) {
            PyObject[] pyArgs = Arrays.stream(testCase.getArgs())
                    .map(Py::java2py)
                    .toArray(PyObject[]::new);
            PyObject result = function.__call__(pyArgs);

            if (!result.toString().equals(testCase.getExpectedResult().toString())) {
               return new ExecuteCodeResponse(false, "Failed on input: " + Arrays.toString(testCase.getArgs()) +
                       " Expected: " + testCase.getExpectedResult() +
                       " but got: " + result.toString());
            }
         }
         return new ExecuteCodeResponse(true, "All test cases passed successfully.");
      }
      catch (Exception e){
         return new ExecuteCodeResponse(false, e.getMessage());
      }
   }

}
