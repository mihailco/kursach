package harakiri.entity.test;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@Builder
public class TestTaking {
   private String userId;
   private Date date;
   private double res;
}
