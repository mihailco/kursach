package harakiri.service;

import harakiri.entity.test.TestHistoryCollection;
import harakiri.repository.TestHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestHistoryService {
    private final TestHistoryRepository testHistoryRepository;

   public TestHistoryCollection save(TestHistoryCollection res) {
   return testHistoryRepository.save(res);
   }
}
