package harakiri.service.Impl;

import harakiri.models.SentMail;
import harakiri.repository.SentEmailRepository;
import harakiri.service.SentMailHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SentMaiHistoryServiceImpl implements SentMailHistoryService {
    private final SentEmailRepository sentEmailRepository;

    @Override
    public SentMail save(SentMail sentMail) {
        return sentEmailRepository.save(sentMail);
    }

    @Override
    public SentMail getById(String id) {
        return sentEmailRepository.findById(id).orElse(null);
    }

    @Override
    public List<SentMail> getAll() {
        return sentEmailRepository.findAll();
    }

    @Override
    public List<SentMail> getMailingHistory(String idTopic) {
        return sentEmailRepository.findAllByidTopic(idTopic);
    }
}
