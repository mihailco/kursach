package harakiri.repository.Impl;

import harakiri.entity.test.TestCollection;
import harakiri.entity.test.TestTaking;
import harakiri.repository.TestDao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class TestDaoImpl implements TestDao {
   private final MongoTemplate mongoTemplate;

   @Override
   public void addTestTaking(TestTaking testTaking, String testId) {
      Criteria criteria = Criteria.where("_id").is(testId);
      Query query = Query.query(criteria);

      Update update = new Update();
      update.push("testTakings", testTaking);

      mongoTemplate.updateFirst(query, update, TestCollection.class);
   }

   @Override
   public TestCollection getTestInfo(String testId, String userId) {
      Criteria c = Criteria.where("_id").is(testId);

      Query q = Query.query(c);

      q.fields().exclude(
              "questionList.correctText",
              "questionList.chooseOption.isCorrect",
              "questionList.sequenceOptions.n",
              "questionList.chooseOption.correct"
      );

      var test =  mongoTemplate.findOne(q, TestCollection.class);
       var testTakings = test.getTestTakings().stream()
               .filter(e -> Objects.equals(e.getUserId(), userId)).toList();
       test.setTestTakings(testTakings);

      return test;
   }
}
