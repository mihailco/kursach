package harakiri.service;

import harakiri.repository.TestHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestHistoryService {
    private final TestHistoryRepository testHistoryRepository;
}
