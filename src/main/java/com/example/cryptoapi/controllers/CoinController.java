package com.example.cryptoapi.controllers;

import com.example.cryptoapi.dto.SymbolPrice;
import com.example.cryptoapi.models.Coin;
import com.example.cryptoapi.services.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/cripto")
public class CoinController {

    private CoinService coinService;

    @Autowired
    public CoinController(CoinService coinService) {
        this.coinService = coinService;
    }

    @GetMapping("/load-coins")
    public List<Coin> loadCoins(){
        return coinService.loadCoins();
    }

    @GetMapping("/coins")
    public List<Coin> getCoins(){
        return coinService.getCoins();
    }

    @GetMapping("/coins/{symbol}")
    public Optional<Coin> getCoin(@PathVariable("symbol") String symbol){
        return coinService.getCoin(symbol);
    }

    @GetMapping("/coins/{symbol}/price")
    public SymbolPrice getCoinValue(@PathVariable("symbol") String symbol){
        return coinService.getCoinValue(symbol);
    }


}
