package harakiri.controller;

import harakiri.dto.MailingsWithSubscription;
import harakiri.dto.SendMailModelRequest;
import harakiri.models.Mailings;
import harakiri.models.SentMail;
import harakiri.security.filter.UserContextHolder;
import harakiri.service.MailingsService;
import harakiri.service.SentMailHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/email/mailings")
@RequiredArgsConstructor
public class MailingsController {
    private final MailingsService mailingsService;
    private final SentMailHistoryService sentMailHistoryService;

    @GetMapping("/all")
    public List<MailingsWithSubscription> getAll() {
        return mailingsService.getAll(String.valueOf(UserContextHolder.getId()));
    }
    @GetMapping("/history/{mailingId}")
    public List<SentMail> getMailingHistory(@PathVariable String mailingId) {
        return sentMailHistoryService.getMailingHistory(mailingId);
    }

    @PostMapping
    public Mailings addMailing(@RequestBody Mailings mailings) {
        return mailingsService.save(mailings);
    }

    @PostMapping("/subscribe/{mailingId}")
    public void subscribe(@PathVariable String mailingId) {
        mailingsService.subscribe(mailingId, UserContextHolder.getId());
    }

    @PostMapping("/unsubscribe/{mailingId}")
    public void unsubscribe(@PathVariable String mailingId) {
        mailingsService.unsubscribe(mailingId, UserContextHolder.getId());
    }

    @PostMapping("/send/{mailingId}")
    public void sendToMailing(@PathVariable String mailingId, @RequestBody SendMailModelRequest sendMailModelRequest) {
        mailingsService.sendToMailing(mailingId, sendMailModelRequest.getMessage());
    }
}
