package harakiri.repository;

import harakiri.model.test.TestHistoryCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestHistoryRepository extends MongoRepository<TestHistoryCollection, String> {
}
