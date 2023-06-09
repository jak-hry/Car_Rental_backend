package com.carrental.scheduler;

import com.carrental.repository.CarRepository;
import com.carrental.repository.RentalRepository;
import com.carrental.scheduler.config.AdminConfig;
import com.carrental.scheduler.domain.Mail;
import com.carrental.scheduler.service.SimpleMailService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

@SpringBootTest
class EmailSchedulerTest {

    @MockBean
    private SimpleMailService mailService;

    @MockBean
    private RentalRepository rentalRepository;

    @MockBean
    private CarRepository carRepository;

    @Test
    void shouldSendInformationEmail() {
        // Given
        EmailScheduler emailScheduler = new EmailScheduler(mailService, rentalRepository, carRepository, new AdminConfig());
        when(rentalRepository.count()).thenReturn(5L);
        when(carRepository.count()).thenReturn(10L);

        // When
        emailScheduler.sendInformationEmail();

        // Then
        verify(mailService, times(1)).send(any(Mail.class));
    }
}