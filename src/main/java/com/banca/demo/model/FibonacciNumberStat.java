package com.banca.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FibonacciNumberStat {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private int number;

    private int occurrence;
}
