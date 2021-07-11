package com.example.cryptoapi.clients;

import com.example.cryptoapi.dto.ExchangeInfoDto;
import com.example.cryptoapi.dto.SymbolPrice;
import com.example.cryptoapi.models.Coin;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BinanceClient {

    private WebClient webClient = null;
    private String binanceUrl = "https://api.binance.com/api/v3";
    private String defaultSymbol = "USDT";

    public BinanceClient() {
        this.webClient = WebClient.builder().baseUrl(this.binanceUrl).exchangeStrategies(ExchangeStrategies.builder().codecs(configurer -> configurer
                .defaultCodecs()
                .maxInMemorySize(16*1024*1024)
        ).build()).build();
    }

    public List<Coin> loadCoins(){
        ExchangeInfoDto exchangeInfoDto = (ExchangeInfoDto) this.webClient.get().uri("/exchangeInfo").retrieve().bodyToMono(ExchangeInfoDto.class).block();

        exchangeInfoDto.getSymbols().removeIf((coin) -> !coin.getQuoteAsset().equals(defaultSymbol));

        List<Coin> coins = new ArrayList<Coin>();

        exchangeInfoDto.getSymbols().forEach((cryptocoin) -> coins.add(new Coin(
                cryptocoin.getSymbol(),
                cryptocoin.getStatus(),
                cryptocoin.getBaseAsset(),
                cryptocoin.getBaseAssetPrecision(),
                cryptocoin.getQuoteAsset(),
                cryptocoin.getQuotePrecision(),
                cryptocoin.getQuoteAssetPrecision(),
                cryptocoin.getBaseCommissionPrecision(),
                cryptocoin.getQuoteCommissionPrecision(),
                cryptocoin.isIcebergAllowed(),
                cryptocoin.isOcoAllowed(),
                cryptocoin.isQuoteOrderQtyMarketAllowed(),
                cryptocoin.isSpotTradingAllowed(),
                cryptocoin.isMarginTradingAllowed()
        )));

        return coins;
    }

    public SymbolPrice getSymbolPrice(String symbol) {
        SymbolPrice symbolPrice = (SymbolPrice) this.webClient.get().uri(uriBuilder -> uriBuilder.path("/ticker/price").queryParam("symbol", symbol).build()).retrieve().bodyToMono(SymbolPrice.class).block();

        return symbolPrice;
    }
}
