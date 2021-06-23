package ru.sber.javaschool.server.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Cards")
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Card {

    @Id
    @GeneratedValue
    private Long id;
    private String ownerName;
    private String ownerSurname;
    private int pin;
    private String number;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false, referencedColumnName = "id")
    private Account account;
}
