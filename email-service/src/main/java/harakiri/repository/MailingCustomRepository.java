package harakiri.repository;

import harakiri.models.Subscriberbd;

public interface MailingCustomRepository {
    void subscribe(String mailingId, Subscriberbd subs);

    void unsubscribe(String mailingId, String subsId);
}
