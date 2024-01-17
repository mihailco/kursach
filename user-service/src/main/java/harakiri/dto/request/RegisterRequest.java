package harakiri.dto.request;


import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.Objects;

@Getter
public class RegisterRequest {
    @NotBlank
    String fio;
    @NotBlank
    String username;
    @NotBlank
    String password;
}
