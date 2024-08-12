package harakiri.entity.test.question;

import lombok.Data;

import java.util.List;

@Data
public class CodeOption {
    private List<CodeTestCase> codeTestCases;
    private String defaultCode;
    private String functionName;
}
