package ru.sber.javaschool.server.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CardDTO {
    private final String ownerName;
    private final String ownerSurname;
    private final int pin;
    private final String number;
}
