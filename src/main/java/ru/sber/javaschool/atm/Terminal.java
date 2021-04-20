package ru.sber.javaschool.atm;

import ru.sber.javaschool.model.Card;


public interface Terminal {

    void start();

  //  boolean checkPin(int pin);

    void showBalance();

    void extractCard(Card currentCard);

    boolean authentication();

}
