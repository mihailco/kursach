package harakiri.service;

import harakiri.model.test.TestCollection;
import harakiri.model.test.TestHistoryCollection;
import harakiri.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    public void save(TestCollection collection) {
        testRepository.save(collection);
    }

    public TestCollection getbyId(String id) {
        return testRepository.findById(new ObjectId(id)).orElse(null);
    }

    public void deleteById(String id) {
        testRepository.deleteById(new ObjectId(id));
    }
}
