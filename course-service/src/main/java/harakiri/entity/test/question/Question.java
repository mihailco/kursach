package harakiri.entity.test.question;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
public class Question {
   private String id = new ObjectId().toString();

   private QuestionType questionType;
   private double questionPoints;

   private String questionText;

   private List<ChoiseOption> chooseOption;

   private List<SequenceOption> sequenceOptions;

   private JuxtapositionOption juxtapositionOption;

   private CodeOption codeOption;


   private String correctText;

}
