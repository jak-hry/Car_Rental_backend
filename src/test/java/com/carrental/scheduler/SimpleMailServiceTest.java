package com.carrental.scheduler;

import com.carrental.scheduler.domain.Mail;
import com.carrental.scheduler.service.SimpleMailService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SimpleMailServiceTest {

    @Test
    void shouldSendEmail() {
        // Given
        JavaMailSender mailSender = mock(JavaMailSender.class);
        SimpleMailService mailService = new SimpleMailService(mailSender);

        Mail mail = new Mail();
        mail.setMailTo("test@example.com");
        mail.setSubject("Test Subject");
        mail.setMessage("Test Message");

        ArgumentCaptor<SimpleMailMessage> mailMessageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);

        // When
        mailService.send(mail);

        // Then
        verify(mailSender, times(1)).send(mailMessageCaptor.capture());

        SimpleMailMessage sentMail = mailMessageCaptor.getValue();
        assertEquals(mail.getMailTo(), Objects.requireNonNull(sentMail.getTo())[0]);
        assertEquals(mail.getSubject(), sentMail.getSubject());
        assertEquals(mail.getMessage(), sentMail.getText());
    }
}