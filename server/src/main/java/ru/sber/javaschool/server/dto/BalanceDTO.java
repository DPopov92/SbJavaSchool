package ru.sber.javaschool.server.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public class BalanceDTO {
    private final long amount;
    private final String currency;

}
