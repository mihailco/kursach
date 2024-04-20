package harakiri.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course/test")
public class TestController {

    @PostMapping
    public ResponseEntity<?> createTest(@RequestBody Object question) {

        return ResponseEntity.ok("created");
    }
}
