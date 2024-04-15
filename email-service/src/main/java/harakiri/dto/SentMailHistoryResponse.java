package harakiri.dto;

import java.util.Date;
import java.util.List;

public class SentMailHistoryResponse {
    private String id;
    private String idTopic;
    private String name;
    private List<String> userIdTo;
    private Date sentAt;
    private String text;
}
