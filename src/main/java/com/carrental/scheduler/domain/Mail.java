package com.carrental.scheduler.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mail {

    private String mailTo;
    private String subject;
    private String message;
    private String[] toCc;
}