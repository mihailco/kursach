package harakiri.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
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
    private String creatorId;
    private BigDecimal price;
}
