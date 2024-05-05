package harakiri.dto.response;

import harakiri.model.course.CourseCollection;
import harakiri.model.test.TestCollection;
import harakiri.model.test.UserSessionCollection;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BeginTestResponse {
    private TestCollection test;
    private UserSessionCollection userSession;
}
