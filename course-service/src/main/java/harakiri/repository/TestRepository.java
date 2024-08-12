package harakiri.repository;

import harakiri.dto.response.TestInfo;
import harakiri.entity.test.TestCollection;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends MongoRepository<TestCollection, String>, TestDao {

}
