package harakiri.feign;

import harakiri.dto.yandex.PromptRequest;
import harakiri.dto.yandex.YandexResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient(name = "yandexFeignClient", url = "https://llm.api.cloud.yandex.net/foundationModels/v1")
public interface YandexFeignClient {


    @PostMapping(value = "/completion", consumes = "application/json")
    YandexResponse getCompletion(
            @RequestHeader("Authorization") String bearerToken,
            @RequestHeader("x-folder-id") String folderId,
            @RequestBody PromptRequest promptRequest);
}
