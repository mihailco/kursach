package harakiri.model.parts;

import com.mongodb.lang.Nullable;
import lombok.Data;

@Data
public class SubsectionContent {
    ContentType contentType;
    String body;
    @Nullable
    String descr;
}
