package com.adp.ADPTest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum Coins {

    TWENTYFIVECENTS(25), TENCENTS(10), FIVECENTS(5), ONECENT(1);
    private int coinValue;

    public int getCoinValue() {
        return coinValue;
    }
}
