package harakiri.service;

import harakiri.config.YandexProperties;
import harakiri.dto.yandex.PromptRequest;
import harakiri.feign.YandexFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class YandexService {
    private final YandexProperties yandexProperties;
    private final YandexFeignClient yandexFeignClient;
    private final  TokenService tokenService;

    public String requestCompletion(String prompt) {
        PromptRequest promptRequest = buildRequest(prompt);

        return yandexFeignClient.
                getCompletion("Bearer " + tokenService.getCurrentToken(),
                        yandexProperties.getFolderId(),
                        promptRequest
                ).getResult().getAlternatives().stream().findFirst()
                .map(e->e.getMessage().getText()).orElse(null);
    }

    private PromptRequest buildRequest(String text) {
        return PromptRequest.builder()
                .modelUri("gpt://b1gebvniis9kf6dhlm57/yandexgpt/rc")
                .completionOptions(
                        PromptRequest.CompletionOptions.builder()
                                .maxTokens(500)
                                .temperature(0.3)
                                .build())
                .messages(Arrays.asList(
                        PromptRequest.Message.builder()
                                .role("user")
                                .text(text)
                                .build()
                ))
                .build();
    }
}
