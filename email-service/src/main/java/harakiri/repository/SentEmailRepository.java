package harakiri.repository;

import harakiri.models.SentMail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SentEmailRepository extends MongoRepository<SentMail, Object> {
    @Query(value =" {'idTopic' : ?0}")
    List<SentMail> findAllByidTopic(String idtopic);
}
