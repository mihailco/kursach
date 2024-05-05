package harakiri.repository;

import harakiri.model.course.CourseCollection;
import harakiri.model.test.TestCollection;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends MongoRepository<TestCollection, ObjectId> {

}
