package harakiri.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "course")
public class CourseCollection {
    @Id
    private String id;

    private String FIO;
    private long creatorId;

    private String tittle;
    private String courseFor;
    private String learningResults;
    private Date createdAt;

    private boolean isBublished;

    private List<Section> sections;

    private BigDecimal price;
}
