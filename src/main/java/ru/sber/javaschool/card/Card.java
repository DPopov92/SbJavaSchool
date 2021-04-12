package ru.sber.javaschool.card;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class Card {
    private LocalDate expireDate;
    private String ownerName;
    private String ownerSurname;
    private BigDecimal balance;
    private int pin;
    private String currency;
}
