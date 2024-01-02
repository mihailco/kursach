package harakiri.model.parts;

import lombok.Data;

import java.util.List;

@Data
public class Section {
    String title;
    String description;
    List<SubSection> subSections;
}
