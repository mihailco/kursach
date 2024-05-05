package harakiri.model.test.question;

import ch.qos.logback.core.joran.sanity.Pair;
import jakarta.persistence.Id;
import lombok.Data;

import javax.sound.midi.Sequence;
import java.util.List;

@Data
public class Question {
    @Id
    private String id;

    private QuestionType type;

    private String questionText;

    private List<ChoiseOption> chooseOption;

    private List<SequenceOption> sequenceOptions;

    private JuxtapositionOption juxtapositionOption;

    private CodeOption codeOption;


    private String correctText;

}
