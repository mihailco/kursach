package harakiri.entity.test.question;

import lombok.Data;

import java.util.List;

@Data
public class JuxtapositionOption {
    private List<String> leftOption;
    private List<String> rightOption;
}
