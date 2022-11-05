package com.adp.ADPTest.service;

import com.adp.ADPTest.model.Bills;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DisplayCoinsService {

    public void displayCoins(Bills bills, int twentyFiveCents, int tenCents, int fiveCents, int oneCent) {
        log.info(" Twenty Five Cents = " + bills.getTwentyFiveCents() + "  Twenty Five Cents in the system: " + twentyFiveCents);
        log.info(" Ten Cents = " + bills.getTenCents() + "  Ten Cents in the system: " + tenCents);
        log.info(" Five Cents = " + bills.getFiveCents() + "  Five Cents in the system:  " + fiveCents);
        log.info(" One Cent  = " + bills.getOneCent() + "  One Cent in the system:  " + oneCent);

    }
}
