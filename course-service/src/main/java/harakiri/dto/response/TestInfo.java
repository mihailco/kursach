package harakiri.dto.response;

import harakiri.entity.test.TestSettings;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestInfo {
   private String title;
   private TestSettings testSettings;
}
