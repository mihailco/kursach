package harakiri.dto;

import lombok.Data;

@Data
public class MailingsWithSubscription {
    private String id;
    private String name;
    private String description;
    private boolean isSubscribed;
}