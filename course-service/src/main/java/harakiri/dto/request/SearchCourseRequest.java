package harakiri.dto.request;

import lombok.Data;

@Data
public class SearchCourseRequest {
   private String fio;
   private String tittle;
   private Integer year;
}
