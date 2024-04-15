package harakiri.service.Impl;

import harakiri.service.BasicMailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
@Service
@RequiredArgsConstructor
public class BasicMailServiceImpl implements BasicMailService {
    private final JavaMailSenderImpl javaMailSender;
    private final TemplateEngine templateEngine;
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public void send(String to, String text, String subject) throws MessagingException {
        log.info(String.format("%s\n%s: %s", to, subject, text));
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setFrom("mcosox@mail.ru");
        mimeMessageHelper.setSubject(subject);


        Context context = new Context();
        context.setVariable("text", text);
        context.setVariable("subject", subject);
        String htmlContent = templateEngine.process("mail-form", context);


        mimeMessageHelper.setText(htmlContent, true);
        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendAsync(String to, String text, String subject) {
        executorService.submit(() -> {
            try {
                send(to, text, subject);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void sendToMany(List<String> to, String text, String subject) {
        to.forEach((e) -> {
            sendAsync(e, text, subject);
        });
    }
}