package harakiri.model.test.question;

import jakarta.persistence.Id;

public class SequenceOption {
    @Id
    private String id;
    private String text;
    private int n;
}
