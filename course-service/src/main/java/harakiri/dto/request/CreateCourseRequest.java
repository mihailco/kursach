package harakiri.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCourseRequest {
    @NotBlank
    private String tittle;
    private String courseFor;
    private String whatLearning;

    @NotBlank
   private String fio;
}
