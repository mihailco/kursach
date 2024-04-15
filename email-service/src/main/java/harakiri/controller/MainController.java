package harakiri.controller;

import harakiri.dto.SendMailModelRequest;
import harakiri.models.SentMail;
import harakiri.service.Impl.BasicMailServiceImpl;
import harakiri.service.Impl.SentMaiHistoryServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
@Controller
@RequestMapping("/api/v1/email")
@RequiredArgsConstructor
public class MainController {
    private final SentMaiHistoryServiceImpl sentMaiHistoryServiceImpl;
    private final BasicMailServiceImpl basicMailServiceImpl;
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    @PostMapping("/send")
    public ResponseEntity<?> feedback(@RequestBody SendMailModelRequest sendMailModel) {
        log.info("email send");
        try {
            String messageToUser = String.format("Вы отправили сообщение: \"%s\".       Ждите ответа", sendMailModel.getMessage());
            String messageToAdmin = String.format("%s отправил сообщение: \"%s\".       Напишите ответ на его почту", sendMailModel.getEmailFrom(), sendMailModel.getMessage());

            basicMailServiceImpl.sendAsync(sendMailModel.getTargetEmail(), messageToUser, "Обращение к администратору");
            basicMailServiceImpl.sendAsync("mcosox@mail.ru", messageToAdmin, "Обращение к администратору");

//            mailService.send();

        } catch (Exception e) {
            System.out.println(e);
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @PostMapping("/admin-send")
    public ResponseEntity<?> adminSend(@RequestBody SendMailModelRequest sendMailModel) {
        try {
            String messageToUser = String.format("Вы получили сообщение: \"%s\".", sendMailModel.getMessage());
            basicMailServiceImpl.send(sendMailModel.getTargetEmail(), messageToUser, "Сообщение от администратора");

            SentMail sentMail = SentMail.builder()
                    .sentAt(new Date())
                    .text(messageToUser)
                    .userIdTo(Collections.singletonList(sendMailModel.getTargetEmail()))
                    .build();
            sentMaiHistoryServiceImpl.save(sentMail);
        } catch (Exception e) {
            System.out.println(e);
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
        return (ResponseEntity<?>) ResponseEntity.ok();
    }
}