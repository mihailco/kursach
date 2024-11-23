package harakiri.controller;

import harakiri.dto.response.DataResponse;
import harakiri.service.YandexService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course/yandex/neural")
public class YandexNeuralController {

    private final YandexService yandexService;

    @GetMapping("/completion")
    public DataResponse getCompletion(@RequestParam String prompt) {
        return new DataResponse(yandexService.requestCompletion(prompt));
    }
}
