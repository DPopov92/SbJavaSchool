package ru.sber.javaschool.atm;

import ru.sber.javaschool.card.Card;

public interface Reader {

    Card getInputCard();

    void extract(Card currentCard);
}
