package com.adp.ADPTest.utils;

import org.springframework.stereotype.Component;

@Component
public class DollarToCentsConverter {

    public static int dollarCentsConverter(int dollars) {
        return 100 * dollars;
    }

}
