package harakiri.repository;

import harakiri.models.Mailings;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MailingRepository extends MongoRepository<Mailings, String>, MailingCustomRepository{
}
