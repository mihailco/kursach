package harakiri.entity.test;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "test-history")
public class TestHistoryCollection {
   @Id
   private String id;
   private double accuracyPercent;
   private List<HistoryOption> answers;
   private TestCollection testCollection;
}

