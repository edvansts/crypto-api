package com.example.cryptoapi.repositories;

import com.example.cryptoapi.models.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {

    @Query("SELECT c FROM Coin c WHERE c.symbol = :symbol")
    Optional<Coin> findCoinBySymbol(String symbol);
}
