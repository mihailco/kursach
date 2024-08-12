package harakiri.service.validator;

import harakiri.dto.response.ExecuteCodeResponse;
import harakiri.entity.test.question.CodeTestCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static harakiri.service.validator.CodeValidatorService.checkPythonFunction;
import static org.junit.jupiter.api.Assertions.*;

class CodeValidatorServiceTest {
   private static String pythonCode;
   private static String functionName;
   private static List<CodeTestCase> testCases;

   @BeforeAll
   public static void setup() {
      // Example Python function to be tested
      pythonCode = "def add(a, b):\n" +
              "    return a + b\n";

      functionName = "add";

      // Create test cases
      testCases = Arrays.asList(
              new CodeTestCase(new Object[]{1, 2}, 3),
              new CodeTestCase(new Object[]{-1, 1}, 0),
              new CodeTestCase(new Object[]{0, 0}, 0),
              new CodeTestCase(new Object[]{100, 200}, 300)
      );
   }

   @Test
   public void testCheckPythonFunction() {
      ExecuteCodeResponse response = checkPythonFunction(pythonCode, functionName, testCases);
      assertTrue(response.isCorrect(), response.getResult());
   }

   @Test
   public void testCheckPythonFunctionWithIncorrectCase() {
      testCases = Arrays.asList(
              new CodeTestCase(new Object[]{1, 2}, 4)
      );

      ExecuteCodeResponse response = checkPythonFunction(pythonCode, functionName, testCases);
      assertFalse(response.isCorrect());
      assertEquals("Failed on input: [1, 2] Expected: 4 but got: 3", response.getResult());
   }
}