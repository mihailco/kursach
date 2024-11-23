package harakiri.service;

import harakiri.entity.test.TestCollection;
import harakiri.entity.test.TestTaking;
import harakiri.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    public void addTestTaking(TestTaking testTaking, String testId) {
        testRepository.addTestTaking(testTaking, testId);
    }

    public TestCollection save(TestCollection collection) {
        if (collection.getQuestionList() != null)
            collection.getQuestionList().forEach(question -> {
                if (question.getId() == null)
                    question.setId(new ObjectId().toString());
            });
        return testRepository.save(collection);
    }

    public TestCollection getbyId(String id) {
        var t = testRepository.findById(id).orElse(null);
        return t;
    }

    public void deleteById(String id) {
        testRepository.deleteById((id));
    }

    public TestCollection getTestInfo(String testId, String userId) {
        return testRepository.getTestInfo(testId, userId);
    }
}
