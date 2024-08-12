package harakiri.entity.test;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "test")
public class TestHistoryCollection {
   private double accuracyPercent;
   private List<HistoryOption> answers;
   private TestCollection testCollection;
}

