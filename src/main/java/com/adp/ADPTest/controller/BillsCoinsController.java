package com.adp.ADPTest.controller;

import com.adp.ADPTest.model.Bills;
import com.adp.ADPTest.service.BillsProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BillsCoinsController {

    @Autowired
    BillsProcessorService billsProcessorService;

    @PostMapping("/processRemainingCoins")
    public Map<String,Integer> remainingCoins(@RequestParam("bills") int bills) throws Exception {
        Map<String, Integer> map=billsProcessorService.processRemainingCoins(new Bills(bills));
        return map;
    }

    @PostMapping("/processExtraCoins")
    public Map<String,Integer> extraCoins(@RequestParam("bills") int bills) throws Exception {
        Map<String, Integer> map=billsProcessorService.processExtraCoins(new Bills(bills));
        return map;
    }
}
