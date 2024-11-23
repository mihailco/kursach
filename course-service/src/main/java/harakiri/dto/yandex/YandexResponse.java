package harakiri.dto.yandex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YandexResponse {
    private Result result;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result {
        private List<Alternative> alternatives;
        private Usage usage;
        private String modelVersion;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Alternative {
        private Message message;
        private String status;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {
        private String role;
        private String text;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Usage {
        private String inputTextTokens;
        private String completionTokens;
        private String totalTokens;
    }
}