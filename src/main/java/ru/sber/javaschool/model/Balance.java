package ru.sber.javaschool.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class Balance {
    private Currency currency;
    private BigDecimal amount;
}
