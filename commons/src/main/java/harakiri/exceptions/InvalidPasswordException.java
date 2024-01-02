package harakiri.exceptions;

import lombok.Getter;

@Getter
public class InvalidPasswordException extends Exception{
    private final String message;

    public InvalidPasswordException(String message) {
        super(message);
        this.message = message;
    }
}
