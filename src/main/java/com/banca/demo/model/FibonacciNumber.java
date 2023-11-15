package com.banca.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="fibonacci_number", schema = "banca")
@AllArgsConstructor
@NoArgsConstructor
public class FibonacciNumber {
    @Id
    private int pos;
    @Column
    private int fibonacci;
}
