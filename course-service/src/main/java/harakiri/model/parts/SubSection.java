package harakiri.model.parts;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubSection {
    String tittle;
    List<? extends SubsectionContent> content = new ArrayList<>();
}
