package harakiri.repository;

import harakiri.model.test.UserSessionCollection;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserSessionRepository extends MongoRepository<UserSessionCollection, String> {

    <S extends UserSessionCollection> S findOneByTestIdAndUserId(String testId, String userId);
}
