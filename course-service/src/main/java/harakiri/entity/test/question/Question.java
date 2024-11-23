package harakiri.entity.test.question;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
public class Question {
   private String id;

   private QuestionType questionType;
   private double questionPoints = 1;

   private String questionText;

   private List<ChoiseOption> chooseOption;

   private List<SequenceOption> sequenceOptions;

   private JuxtapositionOption juxtapositionOption;

   private CodeOption codeOption;


   private String correctText;

}
