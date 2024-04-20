package harakiri.model.course;

import lombok.Data;

@Data
public class Section {
    private SectionType sectionType;
    private String tittle;
    private String description;
    private String fileId;
}
