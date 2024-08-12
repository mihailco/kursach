package harakiri.dto.response;

import harakiri.entity.test.TestCollection;
import harakiri.entity.test.UserSessionCollection;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BeginTestResponse {
    private TestCollection test;
    private UserSessionCollection userSession;
}
