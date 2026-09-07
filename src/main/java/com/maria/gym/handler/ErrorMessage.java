package com.maria.gym.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class ErrorMessage {

    private Integer status;

    private String message;

    private String details;

    private LocalDate timestamp;
}
