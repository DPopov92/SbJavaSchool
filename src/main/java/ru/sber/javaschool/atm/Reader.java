package ru.sber.javaschool.atm;

import ru.sber.javaschool.model.Card;

public interface Reader {

    Card getInputCard();

    void extract(Card currentCard);
}
