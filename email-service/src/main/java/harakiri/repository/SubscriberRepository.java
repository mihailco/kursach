package harakiri.repository;

import harakiri.models.Subscriberbd;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubscriberRepository extends MongoRepository<Subscriberbd, String> {
}
