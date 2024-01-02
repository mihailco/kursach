package harakiri.model;

import harakiri.model.parts.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "course")
public class CourseCollection {

    @Id
    ObjectId id;
    String creatorUserId;

    TittlePage tittlePage;
    SecondPage secondPage;

    String learningResults;
    Creator creator;
    List<String> recenzents;
    String thisCourseFor;

    List<UsedSources> usedSources;

    Date createdAt = new Date();

    List<Section> sections = new ArrayList<>();
    List<String> tags = new ArrayList<>();
}
