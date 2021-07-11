package com.example.cryptoapi.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;

@Getter
@Setter
@ToString
@Entity
@Table(name = "coins")
public class Coin {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    private String symbol;
    private String status;
    private String baseAsset;
    private float baseAssetPrecision;
    private String quoteAsset;
    private float quotePrecision;
    private float quoteAssetPrecision;
    private float baseCommissionPrecision;
    private float quoteCommissionPrecision;
    private boolean icebergAllowed;
    private boolean ocoAllowed;
    private boolean quoteOrderQtyMarketAllowed;
    private boolean isSpotTradingAllowed;
    private boolean isMarginTradingAllowed;

    public Coin(String symbol, String status, String baseAsset, float baseAssetPrecision, String quoteAsset, float quotePrecision, float quoteAssetPrecision, float baseCommissionPrecision, float quoteCommissionPrecision, boolean icebergAllowed, boolean ocoAllowed, boolean quoteOrderQtyMarketAllowed, boolean isSpotTradingAllowed, boolean isMarginTradingAllowed) {
        this.symbol = symbol;
        this.status = status;
        this.baseAsset = baseAsset;
        this.baseAssetPrecision = baseAssetPrecision;
        this.quoteAsset = quoteAsset;
        this.quotePrecision = quotePrecision;
        this.quoteAssetPrecision = quoteAssetPrecision;
        this.baseCommissionPrecision = baseCommissionPrecision;
        this.quoteCommissionPrecision = quoteCommissionPrecision;
        this.icebergAllowed = icebergAllowed;
        this.ocoAllowed = ocoAllowed;
        this.quoteOrderQtyMarketAllowed = quoteOrderQtyMarketAllowed;
        this.isSpotTradingAllowed = isSpotTradingAllowed;
        this.isMarginTradingAllowed = isMarginTradingAllowed;
    }

    public Coin() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coin coin = (Coin) o;
        return id.equals(coin.id) || symbol.equals(coin.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, symbol);
    }
}
