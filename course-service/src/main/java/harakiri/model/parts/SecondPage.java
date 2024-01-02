package harakiri.model.parts;

import lombok.Data;

import java.util.List;

@Data
public class SecondPage {
    String udk;
    String bbk;
    List<String> recenzents;
    String extendedInfo;
    String aboutAuthor;
    String issuesAreCovered;
    String thisCourseFor;
}
