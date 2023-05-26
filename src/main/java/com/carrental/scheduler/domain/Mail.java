package com.carrental.scheduler.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mail {

    private String mailTo;
    private String subject;
    private String message;
    private String[] toCc;
}