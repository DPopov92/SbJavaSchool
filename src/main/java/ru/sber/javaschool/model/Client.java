package ru.sber.javaschool.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;

@Data
@Accessors(chain = true)
public class Client {
    private String clientId;
    private String name;
    private String surname;
    private ArrayList<Phone> phones;
    private ArrayList<Account> accounts;
}
