package harakiri.model.test.question;

import lombok.Data;

import java.util.List;

@Data
public class CodeOption {
    private List<String> validationCode;
    private String defaultCode;

}
