package harakiri.service.Impl;

import harakiri.dto.MailingsWithSubscription;
import harakiri.kafka.messages.UserMessage;
import harakiri.mapper.BasicMapper;
import harakiri.models.Mailings;
import harakiri.models.SentMail;
import harakiri.models.Subscriberbd;
import harakiri.repository.MailingRepository;
import harakiri.service.BasicMailService;
import harakiri.service.MailingsService;
import harakiri.service.SentMailHistoryService;
import harakiri.service.UserServiceRemote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailingServiceImpl implements MailingsService {
    private final MailingRepository mailingRepository;
    private final UserServiceRemote userService;
    private final BasicMailService mailService;
    private final SentMailHistoryService mailHistoryService;
    private final BasicMapper basicMapper;

    @Override
    public Mailings save(Mailings mailings) {
        return mailingRepository.save(mailings);
    }

    @Override
    public void subscribe(String mailingId, Long id) {
        UserMessage user = userService.getUserById(id).orElse(null);
        assert user != null;
        Subscriberbd subs = Subscriberbd.builder()
                .email(user.getUsername())
                .id(String.valueOf(id))
                .build();

        mailingRepository.subscribe(mailingId, subs);
    }

    @Override
    public void unsubscribe(String mailingId, Long id) {
        mailingRepository.unsubscribe(mailingId, String.valueOf(id));

    }

    @Override
    public List<MailingsWithSubscription> getAll(String id) {
        List<Mailings> mailings = mailingRepository.findAll();
        List<MailingsWithSubscription> t = basicMapper.convertListTo(mailings, MailingsWithSubscription.class);
        for (int i = 0; i < t.size(); i++) {
            if (mailings.get(i).getSubscribers() != null) {
                List<String> subs = mailings.get(i).getSubscribers().stream().map(e -> e.getId()).toList();
                t.get(i).setSubscribed(subs.contains(id));
            } else {
                t.get(i).setSubscribed(false);

            }
        }
        return t;
    }

    @Override
    public void sendToMailing(String mailingId, String message) {
        Mailings mailing = mailingRepository.findById(mailingId).orElse(null);
        assert mailing != null;
        if (mailing.getSubscribers() == null) {
            return;
        }
        var mails = mailing.getSubscribers().stream().map(Subscriberbd::getEmail).toList();
        mailService.sendToMany(mails, message, mailing.getName());

        SentMail sentMail = SentMail.builder()
                .sentAt(new Date())
                .text(message)
                .name(mailing.getName())
                .idTopic(mailing.getId())
                .userIdTo(mailing.getSubscribers().stream().map(Subscriberbd::getId).toList())
                .build();

        mailHistoryService.save(sentMail);
    }


}
