package harakiri.repository;

import harakiri.model.course.CourseCollection;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourseRepository extends MongoRepository<CourseCollection, ObjectId>, CourseDao {


    @Query(value = "{}", fields = "{'_id': 1, 'courseFor' :1, '" +
            "createdAt': 1, " +
            "'price': 1, " +
            "'FIO': 1,'tittle': 1, " +
            "'learningResults': 1, " +
            "'price': 1}")
    List<CourseCollection> findAllCourseInfo();

}
