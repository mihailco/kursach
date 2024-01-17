package harakiri.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CourseInfoResponse {
    private String id;
    private String FIO;
    private String tittle;
    private String courseFor;
    private String learningResults;
    private Date createdAt;
}
