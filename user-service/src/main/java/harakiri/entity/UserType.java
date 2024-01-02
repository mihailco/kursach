package harakiri.entity;

import lombok.ToString;

@ToString
public enum UserType {
    ADMIN("admin"), USER("user"), CREATOR("creator");

    private final String creator;

    UserType(String creator) {
        this.creator = creator;
    }
}
