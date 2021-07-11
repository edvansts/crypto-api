package com.example.cryptoapi.services;

import com.example.cryptoapi.clients.BinanceClient;
import com.example.cryptoapi.dto.SymbolPrice;
import com.example.cryptoapi.models.Coin;
import com.example.cryptoapi.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoinService {

    private final CoinRepository coinRepository;
    private final BinanceClient binanceClient;

    @Autowired
    public CoinService(CoinRepository coinRepository, BinanceClient binanceClient) {
        this.coinRepository = coinRepository;
        this.binanceClient = binanceClient;
    }

    public List<Coin> loadCoins() {
        List<Coin> coins = binanceClient.loadCoins();

        if(coins.isEmpty()){
            throw new IllegalStateException("None coin found");
        }

        List<Coin> dbCoins = coinRepository.findAll();

        coins.removeIf((coin) -> dbCoins.stream().anyMatch((dbCoin -> dbCoin.equals(coin))));

        if(coins.isEmpty()){
            throw new IllegalStateException("None new coin found");
        }

        List<Coin> coinsSaved = coinRepository.saveAll(coins);

        return coins;
    }

    public List<Coin> getCoins(){
        return coinRepository.findAll();
    }

    public Optional<Coin> getCoin(String symbol){
        Optional<Coin> coin = coinRepository.findCoinBySymbol(symbol);

        return coin;
    }

    public boolean checkCoinExists(String symbol){
        Optional<Coin> coin = coinRepository.findCoinBySymbol(symbol);

        if(coin.isEmpty()){
            return false;
        }

        return true;
    }

    public SymbolPrice getCoinValue(String symbol) {
        if(!checkCoinExists(symbol)) {
            throw new IllegalStateException("Coin not exists");
        }

        SymbolPrice symbolPrice = binanceClient.getSymbolPrice(symbol);

        if(symbolPrice == null){
            throw new IllegalStateException("Coin price not found");
        }

        return symbolPrice;
    }
}
