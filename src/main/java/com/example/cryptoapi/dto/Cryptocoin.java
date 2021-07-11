package com.example.cryptoapi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
public class Cryptocoin {
    private String symbol;
    private String status;
    private String baseAsset;
    private float baseAssetPrecision;
    private String quoteAsset;
    private float quotePrecision;
    private float quoteAssetPrecision;
    private float baseCommissionPrecision;
    private float quoteCommissionPrecision;
    List<Object> orderTypes;
    private boolean icebergAllowed;
    private boolean ocoAllowed;
    private boolean quoteOrderQtyMarketAllowed;
    private boolean isSpotTradingAllowed;
    private boolean isMarginTradingAllowed;
    List<Object> filters;
    List<Object> permissions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cryptocoin cryptocoin = (Cryptocoin) o;
        return symbol.equals(cryptocoin.symbol) || baseAsset.equals(cryptocoin.baseAsset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseAsset);
    }
}
