package harakiri.service;

import harakiri.models.SentMail;

import java.util.List;

public interface SentMailHistoryService {
    SentMail save(SentMail sentMail);

    SentMail getById(String id);

    List<SentMail> getAll();

    List<SentMail> getMailingHistory(String idTopic);
}
