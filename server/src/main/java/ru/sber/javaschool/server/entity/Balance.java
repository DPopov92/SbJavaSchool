package ru.sber.javaschool.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Balance")
@NoArgsConstructor
@Getter
@Setter
public class Balance {
    @Id
    @GeneratedValue
    private Long id;

    private String currency;
    private long amount;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false, referencedColumnName = "id")
    private Account account;
}
