package com.mthree.DTO;

import java.math.BigDecimal;

public enum Coin {
    DOLLAR(new BigDecimal("1.00")),
    HALF_DOLLAR(new BigDecimal("0.50")),
    QUARTER(new BigDecimal("0.25")),
    DIME(new BigDecimal("0.10")),
    NICKLE(new BigDecimal("0.05")),
    PENNY(new BigDecimal("0.01"));

    private BigDecimal denomination;

    private Coin(BigDecimal denomination){
        this.denomination = denomination;
    }

    public BigDecimal getDenomination(){
        return this.denomination;
    }
}
