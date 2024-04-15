package harakiri.service;

import jakarta.mail.MessagingException;

import java.util.List;

public interface BasicMailService {
    public void send(String to, String text, String subject) throws MessagingException;

    void sendAsync(String to, String text, String subject);

    void sendToMany(List<String> to, String text, String subject);
}
