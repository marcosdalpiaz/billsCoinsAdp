package com.adp.ADPTest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bills {

    private int billValue;
    private int oneCent;
    private int fiveCents;
    private int tenCents;
    private int twentyFiveCents;

    public Bills(Integer billValue) {
        super();
        this.billValue = billValue;
    }

    Map<String, Integer> coinsMapper = new HashMap<>();
}
