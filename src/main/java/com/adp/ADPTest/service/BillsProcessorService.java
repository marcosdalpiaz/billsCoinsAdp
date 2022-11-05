package com.adp.ADPTest.service;

import com.adp.ADPTest.exception.BillsException;
import com.adp.ADPTest.model.Bills;
import com.adp.ADPTest.model.Coins;
import com.adp.ADPTest.utils.Constants;
import com.adp.ADPTest.utils.DollarToCentsConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class BillsProcessorService {

    private static int twentyFiveCents_Coins = Constants.COINS;
    private static int tenCents_Coins = Constants.COINS;
    private static int fiveCents_Coins = Constants.COINS;
    private static int oneCent_Coins = Constants.COINS;
    private int cents;

    private final DisplayCoinsService displayCoinsService;

    public Map<String, Integer> processRemainingCoins(Bills bills) throws BillsException {

        int remainingCents = DollarToCentsConverter.dollarCentsConverter(bills.getBillValue());

        log.info("Bill Amount: " + bills.getBillValue() + " Cents Required:  " + remainingCents + " Cents in System:  "
                + getCentsInSystem());

        if (cents <= remainingCents){
            for (Coins coins : Coins.values()) {
                remainingCents = processNewValue(bills, coins, remainingCents);
            }
        } else {
            throw new BillsException("Insufficient number of coins");
        }

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put(Coins.TWENTYFIVECENTS.name(), bills.getTwentyFiveCents());
        map.put(Coins.TENCENTS.name(), bills.getTenCents());
        map.put(Coins.FIVECENTS.name(), bills.getFiveCents());
        map.put(Coins.ONECENT.name(), bills.getOneCent());
        bills.setCoinsMapper(map);
        displayCoinsService.displayCoins(bills, twentyFiveCents_Coins, tenCents_Coins, fiveCents_Coins, oneCent_Coins);

        return bills.getCoinsMapper();
    }

    public Map<String, Integer> processExtraCoins(Bills bills) throws BillsException {

        int remainingCents = DollarToCentsConverter.dollarCentsConverter(bills.getBillValue());

        log.info("Bill Amount: " + bills.getBillValue() + " Cents Required:  " + remainingCents + " Cents in System:  "
                + getCentsInSystem());

        if (cents <= remainingCents){
            for (int i = Coins.values().length - 1; i >= 0; --i) {
                Coins coins = Coins.values()[i];
                remainingCents = processNewValue(bills, coins, remainingCents);
            }
        } else {
            throw new BillsException("Insufficient number of coins");
        }

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put(Coins.TWENTYFIVECENTS.name(), bills.getTwentyFiveCents());
        map.put(Coins.TENCENTS.name(), bills.getTenCents());
        map.put(Coins.FIVECENTS.name(), bills.getFiveCents());
        map.put(Coins.ONECENT.name(), bills.getOneCent());
        bills.setCoinsMapper(map);
        displayCoinsService.displayCoins(bills, twentyFiveCents_Coins, tenCents_Coins, fiveCents_Coins, oneCent_Coins);

        return bills.getCoinsMapper();
    }

    private int processNewValue(Bills bills, Coins coins, int remainingCents) {

        int systemCoins = getCoins(coins);

        int coinValue = coins.getCoinValue();
        int coinTypeCounter = remainingCents / coinValue;
        ;
        if (coinTypeCounter <= systemCoins) {
            remainingCents = remainingCents % coinValue;
            systemCoins = systemCoins - coinTypeCounter;
        } else {
            remainingCents = remainingCents - (coinValue * systemCoins);
            coinTypeCounter = systemCoins;
            systemCoins = 0;
        }
        setNewValues(bills, coins, systemCoins, coinTypeCounter);

        return remainingCents;
    }

    private void setNewValues(Bills bills, Coins coins, int systemCoins, int coinTypeCounter) {
        if (coins.name().equals(Coins.TWENTYFIVECENTS.name())) {
            twentyFiveCents_Coins = systemCoins;
            bills.setTwentyFiveCents(coinTypeCounter);
        } else if (coins.name().equals(Coins.TENCENTS.name())) {
            tenCents_Coins = systemCoins;
            bills.setTenCents(coinTypeCounter);
        } else if (coins.name().equals(Coins.FIVECENTS.name())) {
            fiveCents_Coins = systemCoins;
            bills.setFiveCents(coinTypeCounter);
        } else if (coins.name().equals(Coins.ONECENT.name())) {
            oneCent_Coins = systemCoins;
            bills.setOneCent(coinTypeCounter);
        }
    }

    private int getCoins(Coins coins) {
        int coinsStarter = 0;
        if (coins.name().equals(Coins.TWENTYFIVECENTS.name()))
            coinsStarter = twentyFiveCents_Coins;
        else if (coins.name().equals(Coins.TENCENTS.name()))
            coinsStarter = tenCents_Coins;
        else if (coins.name().equals(Coins.FIVECENTS.name()))
            coinsStarter = fiveCents_Coins;
        else if (coins.name().equals(Coins.ONECENT.name()))
            coinsStarter = oneCent_Coins;
        return coinsStarter;
    }

    private int getCentsInSystem() {
        cents = (twentyFiveCents_Coins * 25) + (tenCents_Coins * 10) + (fiveCents_Coins * 5)
                + (oneCent_Coins * 1);
        return cents;
    }

}
