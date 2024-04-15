package harakiri.service;

import harakiri.dto.MailingsWithSubscription;
import harakiri.models.Mailings;

import java.util.List;

public interface MailingsService {
    Mailings save(Mailings mailings);

    void subscribe(String mailingId, Long id);


    List<MailingsWithSubscription> getAll(String id);

    void sendToMailing(String mailingId, String message);

    void unsubscribe(String mailingId, Long id);
}
