package com.carrental.scheduler;

import com.carrental.repository.CarRepository;
import com.carrental.repository.RentalRepository;
import com.carrental.scheduler.config.AdminConfig;
import com.carrental.scheduler.domain.Mail;
import com.carrental.scheduler.service.SimpleMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private static final String SUBJECT = "Rental cars: Once a day email";
    private final SimpleMailService simpleMailService;
    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;
    private final AdminConfig adminConfig;

//    @Scheduled(fixedDelay = 10000)
    public void sendInformationEmail() {
        long rentedCarsCount = rentalRepository.count();
        long availableCarsCount = carRepository.count() - rentedCarsCount;

        simpleMailService.send(
                new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        "Number of currently rented cars: " + rentedCarsCount + "\n"
                                + "Number of available cars for rent: " + availableCarsCount,
                        new String[]{}
                ));
    }
}