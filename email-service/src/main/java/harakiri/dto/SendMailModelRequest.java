package harakiri.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendMailModelRequest {
    private String targetEmail;
    private String emailFrom;
    private String message;
}