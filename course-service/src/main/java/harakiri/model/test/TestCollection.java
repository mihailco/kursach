package harakiri.model.test;

import harakiri.model.test.question.Question;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "test")
public class TestCollection {
    @Id
    private String id;
    private String name;
    private String description;
    private List<Question> questionList;
    private TestSettings testSettings;

}
