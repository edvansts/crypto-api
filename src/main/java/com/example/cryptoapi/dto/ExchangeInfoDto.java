package com.example.cryptoapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class ExchangeInfoDto {
    private String timezone;
    private Long serverTime;
    private List<Object> rateLimits;
    private List<Object> exchangeFilters;
    private List<Cryptocoin> symbols;
}
