package harakiri.repository;

import harakiri.entity.test.TestHistoryCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestHistoryRepository extends MongoRepository<TestHistoryCollection, String> {
}
