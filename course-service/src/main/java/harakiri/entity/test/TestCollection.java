package harakiri.entity.test;

import harakiri.entity.test.question.Question;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "test")
public class TestCollection {
    @Id
    private String id;
    private String name;
    private String description;

    private List<Question> questionList;
    private List<TestTaking> testTakings;
    private TestSettings testSettings = new TestSettings();
}
