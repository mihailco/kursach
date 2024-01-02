package harakiri.model.parts;

import lombok.ToString;

@ToString
public enum ContentType {
    LECTION("lection"), TEST("test"), PICTURE("picture"), TABLE("table");

    private final String type;

    ContentType(String type) {
        this.type = type;
    }
}
