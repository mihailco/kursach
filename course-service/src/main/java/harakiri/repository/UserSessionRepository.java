package harakiri.repository;

import harakiri.entity.test.UserSessionCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserSessionRepository extends MongoRepository<UserSessionCollection, String> {

    <S extends UserSessionCollection> S findOneByTestIdAndUserId(String testId, String userId);
    void deleteAllByTestIdAndUserId(String testId, String userId);
}
