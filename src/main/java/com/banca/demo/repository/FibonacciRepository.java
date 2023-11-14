package com.banca.demo.repository;

import com.banca.demo.model.FibonacciNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FibonacciRepository extends JpaRepository<FibonacciNumber, Integer> {
}
