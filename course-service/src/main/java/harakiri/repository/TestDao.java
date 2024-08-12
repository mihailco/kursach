package harakiri.repository;


import harakiri.dto.response.TestInfo;
import harakiri.entity.test.TestCollection;
import harakiri.entity.test.TestTaking;

public interface TestDao {
   void addTestTaking(TestTaking testTaking, String testId);

   TestCollection getTestInfo(String testId, String userId);
}
