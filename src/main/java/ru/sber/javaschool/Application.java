package ru.sber.javaschool;

import ru.sber.javaschool.atm.MainTerminal;

public class Application {

    public static void main(String[] args) {
        MainTerminal mainTerminal = new MainTerminal();
        mainTerminal.start();
        mainTerminal.authentication();
        mainTerminal.showBalance();
    }
}