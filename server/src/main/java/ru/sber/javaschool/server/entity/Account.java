package ru.sber.javaschool.server.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "Accounts")
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client_id;

    @OneToOne(mappedBy = "account")
    private Balance balance;

  //  private int balance;

    private String number;

    @OneToMany(mappedBy = "account")
    private Set<Card> cards;
}
