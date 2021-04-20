package ru.sber.javaschool.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class Card {
    private LocalDate expireDate;
    private String ownerName;
    private String ownerSurname;
    private int pin;
    private String number;
    private String accountId;
    private String clientId;
}
