package harakiri.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
@Builder
public class FileDB {
    @Id
    @GeneratedValue(generator = "uuid")
    private String id;

    private String userId;
    private String courseId;

    private String name;

    private String type;

    @Lob
    private String data;

}
