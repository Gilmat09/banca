package com.banca.demo.repository;

import com.banca.demo.model.FibonacciNumberStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FibonacciNumberStatRepository extends JpaRepository<FibonacciNumberStat,Integer> {
    @Query(value = "Select id,number,count(number) as occurrence from BANCA.fibonacci_number_stat group by number order by occurrence Desc limit :top", nativeQuery = true)
    List<FibonacciNumberStat> getTopNumbers(Integer top);
}
