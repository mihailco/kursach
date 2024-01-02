package harakiri.dto.response;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
@Builder
public class CourseInfo {
    private ObjectId id;
    private String FIO;
    private String tittle;
    private String courseType;
    private String learningResults;
    private Date createdAt;
}
