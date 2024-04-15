package harakiri.repository.impl;

import harakiri.models.Mailings;
import harakiri.models.Subscriberbd;
import harakiri.repository.MailingCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MailingCustomRepositoryImpl implements MailingCustomRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public void subscribe(String mailingId, Subscriberbd subs) {
        Query query = new Query(Criteria.where("id").is(mailingId));
        Update update = new Update();
        update.push("subscribers", subs);
        mongoTemplate.updateFirst(query, update, Mailings.class);
    }

    @Override
    public void unsubscribe(String mailingId, String subsId) {
        Query query = new Query(Criteria.where("id").is(mailingId));
        Update update = new Update().pull("subscribers", Query.query(Criteria.where("id").is(subsId)));
        mongoTemplate.updateFirst(query, update, Mailings.class);
    }
}
